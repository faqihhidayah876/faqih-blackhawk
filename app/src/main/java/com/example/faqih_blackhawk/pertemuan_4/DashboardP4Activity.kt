package com.example.faqih_blackhawk.pertemuan_4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faqih_blackhawk.MainActivity
import com.example.faqih_blackhawk.databinding.ActivityDashboardP4Binding
import com.example.faqih_blackhawk.pertemuan_3.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class DashboardP4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardP4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardP4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol 1: Ke Bangun Ruang
        binding.btnBangunRuang.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("JUDUL", "Volume Balok")
            intent.putExtra("DESKRIPSI", "Ini adalah halaman hitung Volume dari Dashboard.")
            startActivity(intent)
        }

        // Tombol 2: Ke Custom 1
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(this, CustomSatuActivity::class.java)
            intent.putExtra("JUDUL", "Custom UI 1")
            intent.putExtra("DESKRIPSI", "Tampilan Dashboard ala Figma.")
            startActivity(intent)
        }

        // Tombol 3: Ke Custom 2
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(this, CustomDuaActivity::class.java)
            intent.putExtra("JUDUL", "Custom UI 2")
            intent.putExtra("DESKRIPSI", "Tampilan Splash Screen ala Figma.")
            startActivity(intent)
        }

        // Tombol 4: Logout dengan Alert Dialog
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    // Pindah kembali ke LoginActivity (dari pertemuan 3)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Hapus dashboard dari stack
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    // Munculkan Snackbar
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}