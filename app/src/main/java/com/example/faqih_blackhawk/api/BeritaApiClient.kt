package com.example.faqih_blackhawk.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BeritaApiClient {
    private const val BASE_URL = "https://berita-indo-api.vercel.app/"

    val apiService: BeritaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeritaApiService::class.java)
    }
}