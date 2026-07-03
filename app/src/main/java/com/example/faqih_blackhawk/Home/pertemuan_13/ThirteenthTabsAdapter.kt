package com.example.faqih_blackhawk.Home.pertemuan_13

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ThirteenthTabsAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabCaptureFragment()
            1 -> TabQrcodeFragment()
            2 -> TabScanFragment()
            else -> TabCaptureFragment()
        }
    }
}