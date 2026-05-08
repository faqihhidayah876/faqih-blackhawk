package com.example.faqih_blackhawk.Home.pertemuan_8

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.faqih_blackhawk.databinding.ItemAdminBinding
import com.google.android.material.snackbar.Snackbar

class AdminAdapter(
    context: Context,
    private val adminList: List<AdminModel>
) : ArrayAdapter<AdminModel>(context, 0, adminList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 1. Menggunakan ViewBinding persis seperti ajaran dosen
        val binding: ItemAdminBinding = ItemAdminBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        // 2. Mengambil data sesuai urutan (posisi)
        val data = adminList[position]

        // 3. Menampilkan gambar avatar menggunakan Glide dari URL internet
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.imgFoto)

        // 4. Memasukkan teks Nama dan Role
        binding.tvNama.text = data.nama
        binding.tvRole.text = data.role

        // 5. Event OnClick menggunakan Snackbar di dalam Adapter
        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Melihat profil: ${data.nama} - ${data.role}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}