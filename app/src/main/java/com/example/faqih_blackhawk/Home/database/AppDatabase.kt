package com.example.faqih_blackhawk.Home.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CatatanDesaEntity::class, DraftSuratEntity::class], // Daftarkan 2 tabel
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sideraDao(): SideraDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sidera_database"
                )
                    .allowMainThreadQueries()
                    .build().also { INSTANCE = it }
            }
        }
    }
}