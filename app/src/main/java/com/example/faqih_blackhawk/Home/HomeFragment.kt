package com.example.faqih_blackhawk.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.faqih_blackhawk.Home.package_10.BinaDesaActivity
import com.example.faqih_blackhawk.Home.pertemuan_2.KalkulatorActivity
import com.example.faqih_blackhawk.Home.pertemuan_3.LoginActivity
import com.example.faqih_blackhawk.Home.pertemuan_4.CustomDuaActivity
import com.example.faqih_blackhawk.Home.pertemuan_4.CustomSatuActivity
import com.example.faqih_blackhawk.Home.pertemuan_5.WebViewActivity
import com.example.faqih_blackhawk.Home.pertemuan_8.DaftarAdminFragment
import com.example.faqih_blackhawk.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.example.faqih_blackhawk.R

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        binding.btnBangunRuang.setOnClickListener {
            val intent = Intent(requireContext(), KalkulatorActivity::class.java)
            intent.putExtra("JUDUL", "Volume Balok")
            intent.putExtra("DESKRIPSI", "Ini adalah halaman hitung Volume dari Dashboard.")
            startActivity(intent)
        }

        // Tombol 2: Ke Custom 1
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(requireContext(), CustomSatuActivity::class.java)
            intent.putExtra("JUDUL", "Custom UI 1")
            intent.putExtra("DESKRIPSI", "Tampilan Dashboard ala Figma.")
            startActivity(intent)
        }

        // Tombol 3: Ke Custom 2
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(requireContext(), CustomDuaActivity::class.java)
            intent.putExtra("JUDUL", "Custom UI 2")
            intent.putExtra("DESKRIPSI", "Tampilan Splash Screen ala Figma.")
            startActivity(intent)
        }
        // Tombol 4: ke fitur Toolbar & WebView
        binding.btn4.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra("JUDUL", "WebView")
            intent.putExtra("DESKRIPSI", "Tampilan WebView.")
            startActivity(intent)
        }
        binding.btnSearchDesa.setOnClickListener {
            val intent = android.content.Intent(requireContext(), com.example.faqih_blackhawk.Home.pertemuan_8.SearchDesaActivity::class.java)
            startActivity(intent)
        }
        
        binding.btnDaftarAdmin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DaftarAdminFragment()) // R.id.fragment_container adalah wadah di BaseActivity
                .addToBackStack(null) // Agar bisa di-back
                .commit()
        }

        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), BinaDesaActivity::class.java)
            startActivity(intent)
        }

        // 1. Siapkan daftar link/URL gambar (Bisa diganti dengan resource drawable lokal)
        val listBanner = listOf(
            "https://images.unsplash.com/photo-1530878902700-5ad4f9e4c318?q=80&w=1934&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://plus.unsplash.com/premium_photo-1661962862470-a03bcc2fb415?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1600585154340-be6161a56a0c?w=800"  // project mu nanti
        )

        // 2. Pasang Adapter
        val bannerAdapter = BannerHomeAdapter(listBanner)

        // 3. Set Adapter ke RecyclerView
        // (LayoutManager sudah kita atur sebagai horizontal langsung dari XML tadi)
        binding.rvBannerHome.adapter = bannerAdapter

        // Tombol 4: Logout dengan Alert Dialog
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya") { dialog, _ ->

                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()
                    // Pindah kembali ke LoginActivity
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish() // Hapus dashboard dari stack
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}