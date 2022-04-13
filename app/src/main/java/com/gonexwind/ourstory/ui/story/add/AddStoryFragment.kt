package com.gonexwind.ourstory.ui.story.add

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.databinding.FragmentAddStoryBinding
import com.gonexwind.ourstory.ui.story.StoryViewModel
import com.gonexwind.ourstory.utils.Constants
import com.gonexwind.ourstory.utils.Constants.AUTHORITY_CAMERA
import com.gonexwind.ourstory.utils.UserPrefs
import com.gonexwind.ourstory.utils.Utils.createTempFile
import com.gonexwind.ourstory.utils.Utils.reduceFileImage
import com.gonexwind.ourstory.utils.Utils.toast
import com.gonexwind.ourstory.utils.Utils.uriToFile
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@AndroidEntryPoint
class AddStoryFragment : Fragment() {
    private var _binding: FragmentAddStoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StoryViewModel by activityViewModels()

    private var getFile: File? = null
    private lateinit var currentPhotoPath: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = UserPrefs(requireContext()).getToken

        binding.apply {
            backButton.setOnClickListener {
                activity?.onBackPressed()
            }
            cameraButton.setOnClickListener { startCamera() }
            galleryButton.setOnClickListener { startGallery() }
            uploadButton.setOnClickListener { postStory("Bearer $token") }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun postStory(token: String) {
        if (binding.descriptionEditText.text.isEmpty()) {
            val message = getString(R.string.please_fill_out)
            binding.descriptionEditText.error = message
            toast(requireContext(), message)
            return
        }

        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val description = binding.descriptionEditText.text.toString()
            val descriptionPart = description.toRequestBody("text/plain".toMediaType())
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )

            viewModel.postStory(token, imageMultipart, descriptionPart)
                .observe(viewLifecycleOwner) {
                    when (it) {
                        is ApiState.Loading -> {
                            showLoading(true)
                        }
                        is ApiState.Success -> {
                            try {
                                showLoading(false)
                                toast(requireContext(), it.data.message)
                            } finally {
                                findNavController().navigate(R.id.action_addStoryFragment_to_listStoryFragment)
                            }
                        }
                        is ApiState.Error -> {
                            toast(requireContext(), it.message)
                            Log.e(Constants.TAG_ERROR, it.message)
                            showLoading(false)
                        }
                    }
                }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireActivity())
            getFile = myFile
            binding.previewImageView.setImageURI(selectedImg)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager)

        createTempFile(requireActivity().application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                AUTHORITY_CAMERA,
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile

            val result = BitmapFactory.decodeFile(getFile?.path)
            binding.previewImageView.setImageBitmap(result)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.uploadButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.uploadButton.visibility = View.VISIBLE
        }
    }
}
