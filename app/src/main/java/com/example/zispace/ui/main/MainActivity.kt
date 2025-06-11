package com.example.zispace.ui.main

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.zispace.R
import com.example.zispace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    private lateinit var binding: ActivityMainBinding
    private var currentSelectedTab = R.id.nav_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navHostFragment.post {
            setupCustomBottomNavigation()
        }
    }

    private fun setupCustomBottomNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)

        // Set up click listeners for each tab
        binding.navHomeTab.setOnClickListener {
            selectTab(R.id.nav_home)
            navController.navigate(R.id.nav_home)
        }

        binding.navDailyActivityTab.setOnClickListener {
            selectTab(R.id.nav_daily_activity)
            navController.navigate(R.id.nav_daily_activity)
        }

        binding.navGalleryTab.setOnClickListener {
            selectTab(R.id.nav_gallery)
            navController.navigate(R.id.nav_gallery)
        }

        binding.navMusicVideoTab.setOnClickListener {
            selectTab(R.id.nav_music_video)
            navController.navigate(R.id.nav_music_video)
        }

        binding.navProfileTab.setOnClickListener {
            selectTab(R.id.nav_profile)
            navController.navigate(R.id.nav_profile)
        }

        selectTab(R.id.nav_home)

        setTabSelected(R.id.nav_home, true)

        // Listen to navigation changes to update selected tab
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_home -> selectTab(R.id.nav_home)
                R.id.nav_daily_activity -> selectTab(R.id.nav_daily_activity)
                R.id.nav_gallery -> selectTab(R.id.nav_gallery)
                R.id.nav_music_video -> selectTab(R.id.nav_music_video)
                R.id.nav_profile -> selectTab(R.id.nav_profile)
            }
        }
    }

    private fun selectTab(tabId: Int) {
        if (currentSelectedTab == tabId) return

        // Reset all tabs to unselected state
        resetAllTabs()

        // Set selected tab
        currentSelectedTab = tabId
        setTabSelected(tabId, true)
    }

    private fun resetAllTabs() {
        setTabSelected(R.id.nav_home, false)
        setTabSelected(R.id.nav_daily_activity, false)
        setTabSelected(R.id.nav_gallery, false)
        setTabSelected(R.id.nav_music_video, false)
        setTabSelected(R.id.nav_profile, false)
    }

    private fun setTabSelected(tabId: Int, isSelected: Boolean) {
        val (tabContainer, iconView, textView) = when (tabId) {
            R.id.nav_home -> Triple(
                binding.navHomeTab,
                binding.navHomeIcon,
                binding.navHomeText
            )
            R.id.nav_daily_activity -> Triple(
                binding.navDailyActivityTab,
                binding.navDailyActivityIcon,
                binding.navDailyActivityText
            )
            R.id.nav_gallery -> Triple(
                binding.navGalleryTab,
                binding.navGalleryIcon,
                binding.navGalleryText
            )
            R.id.nav_music_video -> Triple(
                binding.navMusicVideoTab,
                binding.navMusicVideoIcon,
                binding.navMusicVideoText
            )
            R.id.nav_profile -> Triple(
                binding.navProfileTab,
                binding.navProfileIcon,
                binding.navProfileText
            )
            else -> return
        }

        iconView.isSelected = isSelected
        textView.isSelected = isSelected

        // Optional: Add visual feedback for selected state
        tabContainer.alpha = if (isSelected) 1.0f else 0.6f
    }
}