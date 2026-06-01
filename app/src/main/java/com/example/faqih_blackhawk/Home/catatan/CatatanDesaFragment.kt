package com.example.faqih_blackhawk.Home.catatan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.faqih_blackhawk.Home.database.AppDatabase
import com.example.faqih_blackhawk.Home.database.CatatanDesaEntity
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.FragmentCatatanDesaBinding
import kotlinx.coroutines.launch

class CatatanDesaFragment : Fragment() {
    private var _binding: FragmentCatatanDesaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CatatanAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<CatatanDesaEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCatatanDesaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        db = AppDatabase.getInstance(requireContext())
        adapter = CatatanAdapter(notes, this)

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvNotes.addItemDecoration(dividerItemDecoration)

        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), CatatanFormActivity::class.java))
        }
    }

    private fun fetchNotes() {
        lifecycleScope.launch {
            val data = db.sideraDao().getAllCatatan()
            notes.clear()
            notes.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deleteNote(note: CatatanDesaEntity) {
        lifecycleScope.launch {
            db.sideraDao().deleteCatatan(note)
            fetchNotes()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}