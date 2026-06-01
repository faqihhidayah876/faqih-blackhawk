package com.example.faqih_blackhawk.Home.draft

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
import com.example.faqih_blackhawk.Home.database.DraftSuratEntity
import com.example.faqih_blackhawk.R
import com.example.faqih_blackhawk.databinding.FragmentDraftSuratBinding
import kotlinx.coroutines.launch

class DraftSuratFragment : Fragment() {
    private var _binding: FragmentDraftSuratBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DraftAdapter
    private lateinit var db: AppDatabase
    private val drafts = mutableListOf<DraftSuratEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDraftSuratBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDraft.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbarDraft.setNavigationOnClickListener {
            parentFragmentManager.popBackStack() // Kembali ke Home Fragment
        }

        db = AppDatabase.getInstance(requireContext())
        adapter = DraftAdapter(drafts, this)

        binding.rvDrafts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrafts.adapter = adapter

        // Garis pemisah antar item
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvDrafts.addItemDecoration(dividerItemDecoration)

        binding.fabAddDraft.setOnClickListener {
            startActivity(Intent(requireContext(), DraftFormActivity::class.java))
        }
    }

    private fun fetchDrafts() {
        lifecycleScope.launch {
            val data = db.sideraDao().getAllDraftSurat()
            drafts.clear()
            drafts.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deleteDraft(draft: DraftSuratEntity) {
        lifecycleScope.launch {
            db.sideraDao().deleteDraftSurat(draft)
            fetchDrafts()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchDrafts() // Memperbarui data setelah dari form
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}