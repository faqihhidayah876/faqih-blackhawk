package com.example.faqih_blackhawk.model

data class BeritaModel(
    val title: String,
    val link: String,
    val contentSnippet: String,
    val isoDate: String,
    val image: ImageModel?
)

data class ImageModel(
    val small: String,
    val large: String
)