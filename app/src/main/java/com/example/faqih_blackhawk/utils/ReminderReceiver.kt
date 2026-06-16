package com.example.faqih_blackhawk.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.faqih_blackhawk.Home.pertemuan_4.DashboardP4Activity

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Pengingat SIDERA"
        val message = intent.getStringExtra("message") ?: "Waktunya mengecek sistem SIDERA."
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            val clazz = Class.forName(targetClassName)
            Intent(context, clazz).apply {
                putExtra("TARGET_FRAGMENT", "HOME_FRAGMENT") // Pastikan sinyal ini ikut terkirim
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        } else {
            Intent(context, com.example.faqih_blackhawk.MainActivity::class.java).apply {
                putExtra("TARGET_FRAGMENT", "HOME_FRAGMENT")
            }
        }

        NotificationHelper.showNotification(context, title, message, targetIntent)
    }
}