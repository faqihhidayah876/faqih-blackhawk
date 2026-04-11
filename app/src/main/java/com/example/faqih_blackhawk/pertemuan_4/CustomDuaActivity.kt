package com.example.faqih_blackhawk.pertemuan_4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivityCustomDuaBinding

class CustomDuaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomDuaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomDuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvJudul.text = intent.getStringExtra("JUDUL")
        binding.tvDesc.text = intent.getStringExtra("DESKRIPSI")
    }
}