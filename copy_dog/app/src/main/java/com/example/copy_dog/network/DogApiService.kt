package com.example.copy_dog.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://dog.ceo/api/"

// ✅ Logging interceptor to see network requests in Logcat
private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

interface DogApiService {
    @GET("breeds/image/random/10")
    suspend fun getPhotos(): DogImage
}

object DogApi {
    val retrofitService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
}