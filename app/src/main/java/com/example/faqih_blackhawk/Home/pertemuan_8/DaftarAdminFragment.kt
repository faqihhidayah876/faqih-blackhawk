package com.example.faqih_blackhawk.Home.pertemuan_8

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.faqih_blackhawk.databinding.FragmentDaftarAdminBinding

class DaftarAdminFragment : Fragment() {

    private var _binding: FragmentDaftarAdminBinding? = null
    private val binding get() = _binding!!

    // Data dummy menggunakan format yang sama persis dengan modul lab
    private val listAdmin = listOf(
        AdminModel("Faqih Hidayah", "Superadmin", "https://ui-avatars.com/api/?name=Faqih+Hidayah&background=random&color=fff"),
        AdminModel("Budi Santoso", "Staff Administrasi", "https://ui-avatars.com/api/?name=Budi+Santoso&background=random&color=fff"),
        AdminModel("Aisyah", "Guest", "https://ui-avatars.com/api/?name=Aisyah&background=random&color=fff"),
        AdminModel("Dika Pratama", "Staff Lapangan", "https://ui-avatars.com/api/?name=Dika+Pratama&background=random&color=fff"),
        AdminModel("Eka Putri", "Sekretaris Desa", "https://ui-avatars.com/api/?name=Eka+Putri&background=random&color=fff")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDaftarAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar ala Fragment (seperti di MessageFragment)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Daftar Admin"
        }

        // Setup tombol back
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Memasang Adapter ke ListView
        val adapter = AdminAdapter(requireContext(), listAdmin)
        binding.lvAdmin.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}