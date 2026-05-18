package com.example.faqih_blackhawk.Home.package_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.faqih_blackhawk.Home.pertemuan_10.DesaModel
import com.example.faqih_blackhawk.databinding.FragmentDaftarDesaBinding

class DaftarDesaFragment : Fragment() {

    private var _binding: FragmentDaftarDesaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaftarDesaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Data Dummy Desa
        val listDesa = listOf(
            DesaModel("Desa Sukamaju", "Kec. Rumbai", "https://plus.unsplash.com/premium_photo-1661962862470-a03bcc2fb415?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            DesaModel("Desa Makmur", "Kec. Tampan", "https://images.unsplash.com/photo-1530878902700-5ad4f9e4c318?q=80&w=1934&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            DesaModel("Desa Sejahtera", "Kec. Tenayan Raya", "https://images.unsplash.com/photo-1600585154340-be6161a56a0c?w=500"),
            DesaModel(
                "Desa Harapan",
                "Kec. Marpoyan",
                "https://images.unsplash.com/photo-1523730205978-59fd1b2965e3?w=500"
            ),
                    DesaModel("Desa Karya Indah", "Kec. Tapung", "https://picsum.photos/500/500?random=10"),
        DesaModel("Desa Pandau Jaya", "Kec. Siak Hulu", "https://picsum.photos/500/500?random=11"),
        DesaModel("Desa Tarai Bangun", "Kec. Tambang", "https://picsum.photos/500/500?random=12"),
        DesaModel("Desa Rimbo Panjang", "Kec. Tambang", "https://picsum.photos/500/500?random=13"),
        DesaModel("Desa Kubang Jaya", "Kec. Siak Hulu", "https://picsum.photos/500/500?random=14"),
        DesaModel("Desa Minas Jaya", "Kec. Minas", "https://picsum.photos/500/500?random=15"),
        DesaModel("Desa Tanah Merah", "Kec. Siak Hulu", "https://picsum.photos/500/500?random=16"),
        DesaModel("Desa Kualu", "Kec. Tambang", "https://picsum.photos/500/500?random=17"),
        DesaModel("Desa Sukamaju", "Kec. Rumbai", "https://picsum.photos/500/500?random=18"),
        DesaModel("Desa Makmur", "Kec. Binawidya", "https://picsum.photos/500/500?random=19"),
        DesaModel("Desa Sejahtera", "Kec. Tenayan Raya", "https://picsum.photos/500/500?random=20"),
        DesaModel("Desa Harapan", "Kec. Marpoyan Damai", "https://picsum.photos/500/500?random=21")
        )

        // Inisialisasi Adapter
        val adapter = DesaAdapter(listDesa) { desa ->
            Toast.makeText(requireContext(), "Kamu memilih ${desa.namaDesa}", Toast.LENGTH_SHORT).show()
        }

        // Set GridLayoutManager dengan Span Count 2
        binding.rvDesa.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvDesa.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}