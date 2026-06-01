package com.example.faqih_blackhawk.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.faqih_blackhawk.Home.berita.BeritaAdapter
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
import com.example.faqih_blackhawk.api.BeritaApiClient
import kotlinx.coroutines.launch

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

        binding.btnCustom1.setOnClickListener {
            val intent = Intent(requireContext(), CustomSatuActivity::class.java)
            intent.putExtra("JUDUL", "Custom UI 1")
            intent.putExtra("DESKRIPSI", "Tampilan Dashboard ala Figma.")
            startActivity(intent)
        }

        binding.btnCustom2.setOnClickListener {
            val intent = Intent(requireContext(), CustomDuaActivity::class.java)
            intent.putExtra("JUDUL", "Custom UI 2")
            intent.putExtra("DESKRIPSI", "Tampilan Splash Screen ala Figma.")
            startActivity(intent)
        }

        binding.btn4.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra("JUDUL", "WebView")
            intent.putExtra("DESKRIPSI", "Tampilan WebView.")
            startActivity(intent)
        }

        binding.btnSearchDesa.setOnClickListener {
            val intent = android.content.Intent(
                requireContext(),
                com.example.faqih_blackhawk.Home.pertemuan_8.SearchDesaActivity::class.java
            )
            startActivity(intent)
        }

        binding.btnDaftarAdmin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    DaftarAdminFragment()
                )
                .addToBackStack(null)
                .commit()
        }

        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), BinaDesaActivity::class.java)
            startActivity(intent)
        }

        val listBanner = listOf(
            "https://images.unsplash.com/photo-1530878902700-5ad4f9e4c318?q=80&w=1934&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://plus.unsplash.com/premium_photo-1661962862470-a03bcc2fb415?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1600585154340-be6161a56a0c?w=800"
        )

        val bannerAdapter = BannerHomeAdapter(listBanner)
        binding.rvBannerHome.adapter = bannerAdapter

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
        // --- TOMBOL FAB SIDERA MENU ---
        binding.fabSideraMenu.setOnClickListener {
            val options = arrayOf("Catatan Perangkat Desa", "Draft Permohonan Surat")

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Pilih Aksi SIDERA")
                .setItems(options) { dialog, which ->
                    when (which) {
                        0 -> {
                            // Pindah ke Fragment Catatan Desa
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragment_container,
                                    com.example.faqih_blackhawk.Home.catatan.CatatanDesaFragment()
                                )
                                .addToBackStack(null)
                                .commit()
                        }
                        1 -> {
                            // Pindah ke Fragment Draft Surat
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragment_container,
                                    com.example.faqih_blackhawk.Home.draft.DraftSuratFragment()
                                )
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                    dialog.dismiss()
                }
                .show()
        }

        // Memuat berita dari API
        loadBerita()
    }

    private fun loadBerita() {
        lifecycleScope.launch {
            try {
                val response = BeritaApiClient.apiService.getBerita()
                val adapter = BeritaAdapter(response.data)
                binding.rvBerita.adapter = adapter
                binding.rvBerita.layoutManager = LinearLayoutManager(requireContext())
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat berita dari API", Toast.LENGTH_SHORT).show()
            }
        }
    }
}