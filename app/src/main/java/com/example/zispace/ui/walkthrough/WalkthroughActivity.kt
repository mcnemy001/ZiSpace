package com.example.zispace.ui.walkthrough

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.zispace.R
import com.example.zispace.databinding.ActivityWalkthroughBinding
import com.example.zispace.ui.main.MainActivity

class WalkthroughActivity : AppCompatActivity() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025



    private lateinit var binding: ActivityWalkthroughBinding
    private lateinit var walkthroughAdapter: WalkthroughAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        //Skip walkthrough jika sudah pernah buka
        val sharedPref = getSharedPreferences("walkthrough", MODE_PRIVATE)
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)

        if (!isFirstTime) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        super.onCreate(savedInstanceState)
        binding = ActivityWalkthroughBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupClickListeners()
    }


    private fun setupViewPager() {
        walkthroughAdapter = WalkthroughAdapter(this)
        binding.viewPager.adapter = walkthroughAdapter

        // Setup page indicator
        binding.pageIndicator.setViewPager2(binding.viewPager)


        // Page change callback
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateButtonText(position)
                updateStatusBar(position)
            }
        })
    }

    private fun setupClickListeners() {
        binding.btnSkip.setOnClickListener {
            navigateToMain()
        }

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < walkthroughAdapter.itemCount - 1) {
                binding.viewPager.currentItem = currentItem + 1
            } else {
                navigateToMain()
            }
        }
    }


    private fun updateButtonText(position: Int) {
        when (position) {
            0 -> {
                // Screen 1 - Dark background
                binding.btnNext.text = ""
                binding.btnSkip.visibility = android.view.View.VISIBLE
                binding.btnNext.setBackgroundResource(R.drawable.btn_secondary_background)
                binding.btnSkip.setBackgroundResource(R.drawable.btn_secondary_background)
                binding.btnNext.setTextColor(resources.getColor(R.color.text_primary_dark, null))
                binding.btnSkip.setTextColor(resources.getColor(R.color.text_primary_dark, null))
                val icon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_right_dark)
                binding.btnNext.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null)
            }
            1 -> {
                // Screen 2 - Light background
                binding.btnNext.text = ""
                binding.btnSkip.visibility = android.view.View.VISIBLE
                binding.btnNext.setBackgroundResource(R.drawable.btn_secondary_dark_background)
                binding.btnSkip.setBackgroundResource(R.drawable.btn_secondary_dark_background)
                binding.btnNext.setTextColor(resources.getColor(R.color.text_primary, null))
                binding.btnSkip.setTextColor(resources.getColor(R.color.text_primary, null))

                val icon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_right_light)
                binding.btnNext.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null)
            }
            2 -> {
                // Screen 3 - Dark background
                binding.btnNext.text = "Let's Go"
                binding.btnSkip.visibility = android.view.View.GONE
                binding.btnNext.setBackgroundResource(R.drawable.btn_secondary_background)
                binding.btnNext.setTextColor(resources.getColor(R.color.text_primary_dark, null))
                val icon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_right_dark)
                binding.btnNext.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null)
            }
        }
    }

    private fun updateStatusBar(position: Int) {
        val window = window
        val decorView = window.decorView
        val controller = WindowInsetsControllerCompat(window, decorView)

        when (position) {
            0, 2 -> {
                // background_2 = gelap
                window.statusBarColor = ContextCompat.getColor(this, R.color.background_2)
                controller.isAppearanceLightStatusBars = false
            }
            1 -> {
                // background_1 = terang
                window.statusBarColor = ContextCompat.getColor(this, R.color.background_1)
                controller.isAppearanceLightStatusBars = true
            }
        }
    }

    private fun navigateToMain() {
        val sharedPref = getSharedPreferences("walkthrough", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isFirstTime", false)
            apply()
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}