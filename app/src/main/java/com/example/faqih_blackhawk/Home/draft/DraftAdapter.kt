package com.example.faqih_blackhawk.Home.draft

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.faqih_blackhawk.Home.database.DraftSuratEntity
import com.example.faqih_blackhawk.databinding.ItemDraftBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DraftAdapter(
    private val drafts: List<DraftSuratEntity>,
    private val fragment: DraftSuratFragment
) : RecyclerView.Adapter<DraftAdapter.DraftViewHolder>() {

    inner class DraftViewHolder(val binding: ItemDraftBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DraftViewHolder {
        val binding = ItemDraftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DraftViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DraftViewHolder, position: Int) {
        val draft = drafts[position]
        holder.binding.tvJenisSurat.text = draft.jenisSurat
        holder.binding.tvNamaPemohon.text = draft.namaPemohon
        holder.binding.tvKeterangan.text = draft.keterangan

        holder.binding.btnDeleteDraft.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Draft")
                .setMessage("Yakin ingin menghapus draft surat ini?")
                .setPositiveButton("Hapus") { dialog, _ ->
                    fragment.deleteDraft(draft)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }

    override fun getItemCount(): Int = drafts.size
}