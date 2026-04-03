package com.example.faqih_blackhawk.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.faqih_blackhawk.databinding.ActivityLoginBinding // Import ini wajib

class LoginActivity : AppCompatActivity() {

    // 1. Deklarasi variable binding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inisialisasi binding (Menggantikan R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root) // WAJIB pakai binding.root

        // 3. Pasang Event Listener langsung panggil id tanpa findViewById
        binding.btnLogin.setOnClickListener {
            val user = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Isi dulu Username dan Password ya!", Toast.LENGTH_SHORT).show()
            } else {
                // Pindah pakai Intent ke halaman Welcome
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}