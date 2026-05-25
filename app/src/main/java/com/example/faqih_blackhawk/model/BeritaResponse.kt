package com.example.faqih_blackhawk.model

data class BeritaResponse(
    val messages: String,
    val total: Int,
    val data: List<BeritaModel>
)