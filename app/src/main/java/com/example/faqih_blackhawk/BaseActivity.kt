package com.example.faqih_blackhawk

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.faqih_blackhawk.About.AboutFragment
import com.example.faqih_blackhawk.Home.HomeFragment
import com.example.faqih_blackhawk.Profile.ProfileFragment
import com.example.faqih_blackhawk.databinding.ActivityBaseBinding
import com.example.faqih_blackhawk.databinding.ActivityFifthBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        replaceFragment(HomeFragment()) //-> jika dipakai, maka awal membuka akan masuk ke home

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false // return false jika item tidak ada yang di klik
            }
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Cek apakah ada lebih dari 1 fragment di dalam tumpukan
                if (supportFragmentManager.backStackEntryCount > 1) {
                    // Jika iya, buang 1 tumpukan teratas (kembali ke fragment sebelumnya)
                    supportFragmentManager.popBackStack()
                } else {
                    // Jika tumpukan hanya tersisa 1 (Fragment awal), maka tutup/keluar dari aplikasi
                    finish()
                }
            }
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null) //-> ini kita nonaktifkan agar saat back langsung keluar aplikasi
            .commit()
    }
}