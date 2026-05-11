package com.example.faqih_blackhawk.Home.quiz

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.ValidationActivity
import com.example.faqih_blackhawk.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.etTanggalLahir.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val tanggal = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.etTanggalLahir.setText(tanggal)
            }, year, month, day).show()
        }
        // Di dalam RegisterActivity.kt bagian btnSelanjutnya.setOnClickListener
        binding.btnSelanjutnya.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val tanggalLahir = binding.etTanggalLahir.text.toString().trim()

            val selectedJkId = binding.rgJenisKelamin.checkedRadioButtonId
            val jenisKelamin = if (selectedJkId != -1) {
                findViewById<RadioButton>(selectedJkId).text.toString()
            } else ""

            val username = binding.etUsernameReg.text.toString().trim()
            val password = binding.etPasswordReg.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            // SIMPAN KE SP SEMENTARA (temp_reg)
            val sharedPref = getSharedPreferences("temp_reg", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("TEMP_NAMA", nama)
            editor.putString("TEMP_EMAIL", email)
            editor.putString("TEMP_TGL", tanggalLahir)
            editor.putString("TEMP_JK", jenisKelamin)
            editor.putString("TEMP_USER", username)
            editor.putString("TEMP_PASS", password)
            editor.putString("TEMP_CONFIRM", confirmPassword)
            editor.apply()

            // PASTIKAN TUJUANNYA KE ValidationActivity
            val intent = Intent(this, ValidationActivity::class.java)
            startActivity(intent)
            // JANGAN panggil finish() di sini agar bisa kembali dari halaman validasi
        }
    }
}