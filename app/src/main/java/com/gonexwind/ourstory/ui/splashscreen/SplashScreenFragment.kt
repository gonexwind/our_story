package com.gonexwind.ourstory.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.utils.UserPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    private lateinit var prefs: UserPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = UserPrefs(requireContext())
        val isLogin = prefs.isLogin
        Handler(Looper.getMainLooper()).postDelayed({
            when {
                isLogin -> findNavController().navigate(R.id.action_splashScreenFragment_to_listStoryFragment)
                else -> findNavController().navigate(R.id.action_splashScreenFragment_to_signInFragment)
            }
        }, 2000)
    }
}