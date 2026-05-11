package com.example.faqih_blackhawk.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faqih_blackhawk.BaseActivity
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivityLoginBinding // Import ini wajib
import com.example.faqih_blackhawk.Home.pertemuan_4.DashboardP4Activity
import com.example.faqih_blackhawk.Home.quiz.RegisterActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.tvKeRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val inputUser = binding.etUsername.text.toString().trim()
            val inputPass = binding.etPassword.text.toString().trim()

            // Ambil data akun yang sudah sukses divalidasi (Soal b2)
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val savedUser = sharedPref.getString("REG_USERNAME", "")
            val savedPass = sharedPref.getString("REG_PASSWORD", "")

            // Rule Soal b3:
            // 1. username == password
            // 2. username & password sesuai yang tersimpan di SP
            val rulePraktikum = (inputUser == inputPass && inputUser.isNotEmpty())
            val ruleRegistrasi = (inputUser == savedUser && inputPass == savedPass && savedUser!!.isNotEmpty())

            if (rulePraktikum || ruleRegistrasi) {
                // Berhasil: Arahkan ke halaman Home (BaseActivity)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply()

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Gagal: Tampilkan pesan error dengan MaterialAlertDialog
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau Password yang Anda masukkan salah atau belum terdaftar!")
                    .setPositiveButton("Tutup") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}