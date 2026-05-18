package com.example.faqih_blackhawk.Home.package_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.faqih_blackhawk.Home.pertemuan_10.DesaModel
import com.example.faqih_blackhawk.databinding.ItemDesaBinding

class DesaAdapter(
    private val listDesa: List<DesaModel>,
    private val onItemClick: (DesaModel) -> Unit
) : RecyclerView.Adapter<DesaAdapter.DesaViewHolder>() {

    inner class DesaViewHolder(val binding: ItemDesaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesaViewHolder {
        val binding = ItemDesaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DesaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DesaViewHolder, position: Int) {
        val desa = listDesa[position]
        with(holder.binding) {
            tvNamaDesa.text = desa.namaDesa
            tvInfoDesa.text = desa.infoDesa

            // Load gambar menggunakan Glide
            Glide.with(holder.itemView.context)
                .load(desa.imageUrl)
                .into(imgDesa)

            root.setOnClickListener {
                onItemClick(desa)
            }
        }
    }

    override fun getItemCount(): Int = listDesa.size
}