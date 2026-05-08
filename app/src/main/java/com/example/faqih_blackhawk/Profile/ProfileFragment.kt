package com.example.faqih_blackhawk.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.faqih_blackhawk.Home.pertemuan_8.SettingsFragment
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Set Title langsung (TANPA setSupportActionBar)
        binding.toolbar.title = "Profile"

        // 2. Tampilkan Menu Titik Tiga
        binding.toolbar.inflateMenu(R.menu.main_menu)

        // 3. Tombol Back
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // 4. Aksi saat Menu Titik Tiga diklik
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_settings -> {
                    // Pindah ke SettingsFragment
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SettingsFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}