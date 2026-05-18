package com.example.faqih_blackhawk.Home.package_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.faqih_blackhawk.databinding.FragmentLayananDesaBinding

class LayananDesaFragment : Fragment() {

    private var _binding: FragmentLayananDesaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLayananDesaBinding.inflate(inflater, container, false)
        return binding.root
    }
}