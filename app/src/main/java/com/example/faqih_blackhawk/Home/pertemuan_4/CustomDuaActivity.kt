package com.example.faqih_blackhawk.Home.pertemuan_4

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivityCustomDuaBinding
import com.example.faqih_blackhawk.databinding.ActivityFifthBinding

class CustomDuaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomDuaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCustomDuaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val judul = intent.getStringExtra("JUDUL")
        val deskripsi = intent.getStringExtra("DESKRIPSI")

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = judul ?: "Custom UI 2"
            subtitle = "Splash Screen"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.tvJudul.text = intent.getStringExtra("JUDUL")
        binding.tvDesc.text = intent.getStringExtra("DESKRIPSI")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}