package com.example.faqih_blackhawk.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faqih_blackhawk.MainActivity
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivityDashboardP4Binding
import com.example.faqih_blackhawk.pertemuan_2.KalkulatorActivity
import com.example.faqih_blackhawk.pertemuan_3.LoginActivity
import com.example.faqih_blackhawk.pertemuan_5.FifthActivity
import com.example.faqih_blackhawk.pertemuan_5.WebViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class DashboardP4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardP4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardP4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Setup Toolbar (Sama seperti FifthActivity)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Dashboard"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // Tombol 1: Ke Bangun Ruang
        binding.btnBangunRuang.setOnClickListener {
            val intent = Intent(this, KalkulatorActivity::class.java)
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
        // Tombol 4: ke fitur Toolbar & WebView
        binding.btn4.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("JUDUL", "WebView")
            intent.putExtra("DESKRIPSI", "Tampilan WebView.")
            startActivity(intent)
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Tombol 4: Logout dengan Alert Dialog
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya") { dialog, _ ->

                    // --- TAMBAHKAN KODE DARI KELASMU DI SINI ---
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    // ------------------------------------------

                    dialog.dismiss()
                    // Pindah kembali ke LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Hapus dashboard dari stack
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Memanggil menu dari res/menu/main_menu.xml
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Logika untuk tombol "Back" (panah kiri) di Toolbar
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}