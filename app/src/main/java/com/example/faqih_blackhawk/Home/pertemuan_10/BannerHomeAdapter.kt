package com.example.faqih_blackhawk.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.faqih_blackhawk.R

class BannerHomeAdapter(private val listGambarUrl: List<String>) : RecyclerView.Adapter<BannerHomeAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBanner: ImageView = itemView.findViewById(R.id.imgBanner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        // Karena kita tidak pakai ViewBinding untuk item kecil ini, kita pakai inflatter biasa
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner_home, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val urlGambar = listGambarUrl[position]

        // Memuat gambar ke ImageView menggunakan Glide
        Glide.with(holder.itemView.context)
            .load(urlGambar)
            .into(holder.imgBanner)
    }

    override fun getItemCount(): Int = listGambarUrl.size
}