package com.example.faqih_blackhawk.Home.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draft_surat")
data class DraftSuratEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val jenisSurat: String,
    val namaPemohon: String,
    val keterangan: String,
    val createdAt: Long
)