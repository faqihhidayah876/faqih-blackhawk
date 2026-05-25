package com.example.faqih_blackhawk.Home.onboard_desa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.faqih_blackhawk.Home.pertemuan_3.LoginActivity
import com.example.faqih_blackhawk.R

class Onboard3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment ini
        val view = inflater.inflate(R.layout.fragment_onboard3, container, false)

        // Inisialisasi tombol
        val btnMulai = view.findViewById<Button>(R.id.btnMulai)

        // Set click listener untuk pindah ke LoginActivity
        btnMulai.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Menutup layar onboard agar tidak kembali saat tekan back
        }

        return view
    }
}