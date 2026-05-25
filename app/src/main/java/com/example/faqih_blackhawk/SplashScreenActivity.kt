package com.example.faqih_blackhawk

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.faqih_blackhawk.Home.onboard_desa.OnboardActivity // Import halaman Onboard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            // Jika sudah login, langsung ke Dashboard (BaseActivity)
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
            finish()
            return // Mencegah kode di bawahnya ikut tereksekusi
        }

        // Jika belum login, arahkan ke OnboardActivity setelah 2 detik
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@SplashScreenActivity, OnboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}