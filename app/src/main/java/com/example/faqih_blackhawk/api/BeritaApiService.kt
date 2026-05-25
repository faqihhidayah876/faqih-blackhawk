package com.example.faqih_blackhawk.api

import com.example.faqih_blackhawk.model.BeritaResponse
import retrofit2.http.GET

interface BeritaApiService {
    @GET("v1/cnn-news")
    suspend fun getBerita(): BeritaResponse
}