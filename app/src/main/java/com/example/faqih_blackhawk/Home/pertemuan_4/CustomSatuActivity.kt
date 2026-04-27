package com.example.faqih_blackhawk.Home.pertemuan_4

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivityCustomSatuBinding

class CustomSatuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomSatuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCustomSatuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val judul = intent.getStringExtra("JUDUL")
        val deskripsi = intent.getStringExtra("DESKRIPSI")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = judul ?: "Kalkulator"
            subtitle = deskripsi ?: "Menghitung luas dan volume"
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