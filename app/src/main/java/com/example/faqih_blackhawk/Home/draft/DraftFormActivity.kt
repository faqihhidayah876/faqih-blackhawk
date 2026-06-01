package com.example.faqih_blackhawk.Home.draft

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.faqih_blackhawk.Home.database.AppDatabase
import com.example.faqih_blackhawk.Home.database.DraftSuratEntity
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.ActivityDraftFormBinding
import kotlinx.coroutines.launch

class DraftFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDraftFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDraftFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        db = AppDatabase.getInstance(this)

        binding.btnSaveDraft.setOnClickListener {
            val jenisSurat = binding.etJenisSurat.text.toString()
            val namaPemohon = binding.etNamaPemohon.text.toString()
            val keterangan = binding.etKeterangan.text.toString()

            if (jenisSurat.isNotBlank() && namaPemohon.isNotBlank()) {
                lifecycleScope.launch {
                    val draft = DraftSuratEntity(
                        jenisSurat = jenisSurat,
                        namaPemohon = namaPemohon,
                        keterangan = keterangan,
                        createdAt = System.currentTimeMillis()
                    )
                    db.sideraDao().insertDraftSurat(draft)
                    finish() // Tutup halaman setelah simpan
                }
            } else {
                Toast.makeText(this, "Jenis Surat dan Nama wajib diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}