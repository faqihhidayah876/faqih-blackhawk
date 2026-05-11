package com.example.faqih_blackhawk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faqih_blackhawk.Home.pertemuan_3.LoginActivity
import com.example.faqih_blackhawk.databinding.ActivityValidationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ValidationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityValidationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mencegah error tampilan (wajib karena XML punya id/main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Ambil data dari SP Sementara (temp_reg)
        val tempSp = getSharedPreferences("temp_reg", Context.MODE_PRIVATE)
        val nama = tempSp.getString("TEMP_NAMA", "") ?: ""
        val email = tempSp.getString("TEMP_EMAIL", "") ?: ""
        val tgl = tempSp.getString("TEMP_TGL", "") ?: ""
        val jk = tempSp.getString("TEMP_JK", "") ?: ""
        val user = tempSp.getString("TEMP_USER", "") ?: ""
        val pass = tempSp.getString("TEMP_PASS", "") ?: ""
        val confirm = tempSp.getString("TEMP_CONFIRM", "") ?: ""

        // 2. Tampilkan isian di TextView
        binding.apply {
            tvValNama.text = "Nama: $nama"
            tvValEmail.text = "Email: $email"
            tvValTgl.text = "Tanggal Lahir: $tgl"
            tvValJk.text = "Jenis Kelamin: $jk"
            tvValUser.text = "Username: $user"
        }

        // 3. Tombol Kembali (Menutup Validasi, form sebelumnya di Register tidak akan hilang)
        binding.btnKembali.setOnClickListener {
            finish()
        }

        // 4. Tombol Submit (Validasi Akhir sesuai Soal b2)
        binding.btnSubmit.setOnClickListener {
            // Rule: Semua inputan tidak boleh kosong
            if (nama.isEmpty() || email.isEmpty() || tgl.isEmpty() || jk.isEmpty() ||
                user.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {

                showErrorDialog("Semua form inputan wajib diisi, tidak boleh ada yang kosong!")
                return@setOnClickListener
            }

            // Rule: Password dan Confirm Password harus sama
            if (pass != confirm) {
                showErrorDialog("Password dan Konfirmasi Password tidak cocok!")
                return@setOnClickListener
            }

            // JIKA BERHASIL MELEWATI VALIDASI: Simpan ke SP permanen ("user_pref")
            val userPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val editor = userPref.edit()
            editor.putString("REG_USERNAME", user)
            editor.putString("REG_PASSWORD", pass)
            editor.apply()

            // Tampilkan Halaman Informasi Registrasi Berhasil (Menggunakan AlertDialog)
            MaterialAlertDialogBuilder(this)
                .setTitle("Registrasi Berhasil")
                .setMessage("Validasi sukses! Akun Anda telah terdaftar. Silakan login untuk melanjutkan.")
                .setCancelable(false)
                .setPositiveButton("Ke Halaman Login") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    // Baris di bawah ini untuk menghapus riwayat halaman, agar user tidak bisa back ke registrasi
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .show()
        }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Validasi Gagal")
            .setMessage(message)
            .setPositiveButton("Kembali") { dialog, _ ->
                dialog.dismiss()
                // Tidak perlu finish() di sini, cukup tutup dialog agar user bisa menekan tombol btnKembali
            }
            .show()
    }
}