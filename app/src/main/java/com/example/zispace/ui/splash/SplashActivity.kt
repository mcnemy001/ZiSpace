package com.example.zispace.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.zispace.R
import com.example.zispace.databinding.ActivitySplashBinding
import com.example.zispace.ui.walkthrough.WalkthroughActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        binding.splashLogo.startAnimation(slideIn)

        val window = window
        val decorView = window.decorView
        val controller = WindowInsetsControllerCompat(window, decorView)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_1)
        controller.isAppearanceLightStatusBars = true

        // Splash selama 3 detik
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            startActivity(Intent(this@SplashActivity, WalkthroughActivity::class.java))
            finish()
        }


    }
}