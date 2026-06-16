package com.example.faqih_blackhawk.Home.catatan

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.faqih_blackhawk.Home.database.AppDatabase
import com.example.faqih_blackhawk.Home.database.CatatanDesaEntity
import com.example.faqih_blackhawk.Home.pertemuan_4.DashboardP4Activity
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivityCatatanFormBinding
import com.example.faqih_blackhawk.utils.NotificationHelper
import com.example.faqih_blackhawk.utils.PermissionHelper
import kotlinx.coroutines.launch

class CatatanFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCatatanFormBinding
    private lateinit var db: AppDatabase

    // 1. Launcher untuk meminta izin notifikasi
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Toast.makeText(this, "Izin notifikasi ditolak!", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatatanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        db = AppDatabase.getInstance(this)

        // 2. Minta Izin Notifikasi saat halaman terbuka
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        // 3. Logika Klik Tombol Simpan
        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                lifecycleScope.launch {

                    // A. Simpan ke Room Database
                    val note = CatatanDesaEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )
                    db.sideraDao().insertCatatan(note)

                    // B. Munculkan Notifikasi Instan
                    val intentKeDashboard = Intent(this@CatatanFormActivity, DashboardP4Activity::class.java).apply {
                        putExtra("TARGET_FRAGMENT", "HOME_FRAGMENT")
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    NotificationHelper.showNotification(
                        context = this@CatatanFormActivity,
                        title = "Catatan Berhasil Disimpan \uD83D\uDCDD",
                        message = "Catatan '$title' telah diamankan ke dalam sistem SIDERA.",
                        intent = intentKeDashboard
                    )

                    Toast.makeText(this@CatatanFormActivity, "Berhasil disimpan", Toast.LENGTH_SHORT).show()
                    finish() // Tutup form
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}