package com.example.vasupractice.api

import com.example.vasupractice.model.MemesRes
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("get_memes")
    suspend fun getUsers(): Response<MemesRes>  // Example API call to get users

    // Add other API endpoints and corresponding methods here
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.imgflip.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

