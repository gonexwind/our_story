package com.gonexwind.ourstory.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.databinding.FragmentMapsBinding
import com.gonexwind.ourstory.utils.UserPrefs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MapsViewModel by activityViewModels()
    private lateinit var googleMap: GoogleMap
    private lateinit var stories: List<Story>

    private fun createMarker(lat: Double, lon: Double, title: String, description: String) =
        googleMap.addMarker(
            MarkerOptions().position(LatLng(lat, lon)).title(title).snippet(description)
        )

    private val callback = OnMapReadyCallback {
        googleMap = it

        it.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-2.3932797, 108.8507139), 6f))
        it.uiSettings.isZoomControlsEnabled = true
        it.uiSettings.isIndoorLevelPickerEnabled = true
        it.uiSettings.isCompassEnabled = true
        it.uiSettings.isMapToolbarEnabled = true

        getMyLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMapsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.map_story)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        val userPrefs = UserPrefs(requireContext())
        getMapStories("Bearer ${userPrefs.getToken}")
    }

    private fun getMapStories(token: String) {
        viewModel.getMapStories(token).observe(viewLifecycleOwner) {
            when (it) {
                is ApiState.Loading -> {
                    showLoading(true)
                }
                is ApiState.Success -> {
                    showLoading(false)
                    stories = it.data.listStory
                    stories.forEach { story ->
                        createMarker(
                            story.lat,
                            story.lon,
                            story.name,
                            story.description
                        )
                    }
                }
                is ApiState.Error -> {
                    showLoading(false)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.map.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.map.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}