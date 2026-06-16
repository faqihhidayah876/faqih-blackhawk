package com.example.faqih_blackhawk.Home.draft

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.faqih_blackhawk.Home.database.AppDatabase
import com.example.faqih_blackhawk.Home.database.DraftSuratEntity
import com.example.faqih_blackhawk.Home.pertemuan_4.DashboardP4Activity
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivityDraftFormBinding
import com.example.faqih_blackhawk.utils.PermissionHelper
import com.example.faqih_blackhawk.utils.ReminderHelper
import kotlinx.coroutines.launch
import java.util.Calendar

class DraftFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDraftFormBinding
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
        binding = ActivityDraftFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        db = AppDatabase.getInstance(this)

        // 2. Minta Izin Notifikasi saat halaman form dibuka
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        binding.btnSaveDraft.setOnClickListener {
            val jenisSurat = binding.etJenisSurat.text.toString()
            val namaPemohon = binding.etNamaPemohon.text.toString()
            val keterangan = binding.etKeterangan.text.toString()
            val menitInput = binding.etPengingatMenit.text.toString() // Ambil nilai menit dari inputan

            if (jenisSurat.isNotBlank() && namaPemohon.isNotBlank()) {
                lifecycleScope.launch {

                    // A. Simpan data ke Room Database
                    val draft = DraftSuratEntity(
                        jenisSurat = jenisSurat,
                        namaPemohon = namaPemohon,
                        keterangan = keterangan,
                        createdAt = System.currentTimeMillis()
                    )
                    db.sideraDao().insertDraftSurat(draft)

                    // B. Set Custom Reminder jika inputan menit tidak kosong
                    if (menitInput.isNotBlank()) {
                        val menit = menitInput.toInt()
                        val calendar = Calendar.getInstance().apply {
                            add(Calendar.MINUTE, menit) // Set waktu alarm sesuai inputan
                        }

                        ReminderHelper.setReminder(
                            context = this@DraftFormActivity,
                            hour = calendar.get(Calendar.HOUR_OF_DAY),
                            minute = calendar.get(Calendar.MINUTE),
                            title = "Proses Draft: $jenisSurat \uD83D\uDCE9",
                            message = "Waktunya memproses draft surat atas nama $namaPemohon.",
                            targetActivity = DashboardP4Activity::class.java
                        )
                        Toast.makeText(this@DraftFormActivity, "Tersimpan! Reminder diatur $menit menit kemudian.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@DraftFormActivity, "Tersimpan tanpa reminder.", Toast.LENGTH_SHORT).show()
                    }

                    finish() // Tutup halaman setelah simpan
                }
            } else {
                Toast.makeText(this, "Jenis Surat dan Nama wajib diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}