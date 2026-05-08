package com.example.faqih_blackhawk.Home.pertemuan_8

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivitySearchDesaBinding

class SearchDesaActivity : AppCompatActivity() {

    // Deklarasi ViewBinding
    private lateinit var binding: ActivitySearchDesaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi ViewBinding
        binding = ActivitySearchDesaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mengaktifkan tombol back pada Toolbar
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Contoh fungsi klik pada tombol pencarian
        binding.btnCariSekarang.setOnClickListener {
            Toast.makeText(this, "Fitur pencarian data sedang diproses", Toast.LENGTH_SHORT).show()
        }
    }
}