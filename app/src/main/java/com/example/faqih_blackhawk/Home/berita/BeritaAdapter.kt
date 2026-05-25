package com.example.faqih_blackhawk.Home.berita

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.faqih_blackhawk.databinding.ItemBeritaBinding
import com.example.faqih_blackhawk.model.BeritaModel

class BeritaAdapter(private val items: List<BeritaModel>) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    inner class BeritaViewHolder(val binding: ItemBeritaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvJudulBerita.text = item.title
        holder.binding.tvSnippet.text = item.contentSnippet

        // Memuat gambar berita
        val imageUrl = item.image?.small ?: item.image?.large
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.binding.imgBerita)
    }

    override fun getItemCount(): Int = items.size
}