package com.example.faqih_blackhawk.Home.package_10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BinaDesaTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Kita punya 2 Tab
    override fun getItemCount(): Int = 2

    // Hubungkan posisi dengan Fragment
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DaftarDesaFragment()
            1 -> LayananDesaFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}