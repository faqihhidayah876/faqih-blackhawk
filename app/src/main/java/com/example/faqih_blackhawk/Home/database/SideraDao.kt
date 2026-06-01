package com.example.faqih_blackhawk.Home.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SideraDao {
    // ---- DAO Catatan Desa ----
    @Query("SELECT * FROM catatan_desa ORDER BY createdAt DESC")
    fun getAllCatatan(): List<CatatanDesaEntity>

    @Insert
    fun insertCatatan(catatan: CatatanDesaEntity)

    @Delete
    fun deleteCatatan(catatan: CatatanDesaEntity)

    // ---- DAO Draft Surat ----
    @Query("SELECT * FROM draft_surat ORDER BY createdAt DESC")
    fun getAllDraftSurat(): List<DraftSuratEntity>

    @Insert
    fun insertDraftSurat(draft: DraftSuratEntity)

    @Delete
    fun deleteDraftSurat(draft: DraftSuratEntity)
}