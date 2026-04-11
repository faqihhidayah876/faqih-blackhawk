package com.example.faqih_blackhawk // Sesuaikan jika penamaan packagemu berbeda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //menerima data intent
        val judul = intent.getStringExtra("JUDUL")
        val deskripsi = intent.getStringExtra("DESKRIPSI")

        // Memunculkan judul dan deskripsi yang dibawa dari Dashboard lewat Toast
        if (judul != null && deskripsi != null) {
            Toast.makeText(this, "Halaman: $judul\n$deskripsi", Toast.LENGTH_LONG).show()
        }

        // --- DEKLARASI KOMPONEN SEGITIGA ---
        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggiSegitiga = findViewById<EditText>(R.id.etTinggiSegitiga)
        val btnHitungSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)
        val tvHasilSegitiga = findViewById<TextView>(R.id.tvHasilSegitiga)

        // Event Listener Segitiga
        btnHitungSegitiga.setOnClickListener {
            val alasStr = etAlas.text.toString()
            val tinggiStr = etTinggiSegitiga.text.toString()

            if (alasStr.isEmpty() || tinggiStr.isEmpty()) {
                Toast.makeText(this, "Alas dan Tinggi Segitiga tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val luas = 0.5 * alasStr.toDouble() * tinggiStr.toDouble()
                tvHasilSegitiga.text = "Hasil: $luas"
            }
        }

        // --- DEKLARASI KOMPONEN BALOK ---
        val etPanjang = findViewById<EditText>(R.id.etPanjang)
        val etLebar = findViewById<EditText>(R.id.etLebar)
        val etTinggiBalok = findViewById<EditText>(R.id.etTinggiBalok)
        val btnHitungBalok = findViewById<Button>(R.id.btnHitungBalok)
        val tvHasilBalok = findViewById<TextView>(R.id.tvHasilBalok)

        // Event Listener Balok
        btnHitungBalok.setOnClickListener {
            val pStr = etPanjang.text.toString()
            val lStr = etLebar.text.toString()
            val tStr = etTinggiBalok.text.toString()

            if (pStr.isEmpty() || lStr.isEmpty() || tStr.isEmpty()) {
                Toast.makeText(this, "Panjang, Lebar, dan Tinggi Balok harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val volume = pStr.toDouble() * lStr.toDouble() * tStr.toDouble()
                tvHasilBalok.text = "Hasil: $volume"
            }
        }
    }
}