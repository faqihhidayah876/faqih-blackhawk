package com.example.faqih_blackhawk.Home.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catatan_desa")
data class CatatanDesaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long
)