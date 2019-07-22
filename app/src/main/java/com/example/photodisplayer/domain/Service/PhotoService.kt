package com.example.photodisplayer.domain.Service

import com.example.photodisplayer.BuildConfig
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("search")
    fun searchPhoto(@Query("query") query: String, @Query("per_page") per_page: Int, @Query("page") page: Int): Single<PhotoSearchDTO.Photos>

    companion object {
        fun create(): PhotoService {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", BuildConfig.PexelsApiKey)
                    .build()
                chain.proceed(newRequest)
            }.build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.pexels.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client).build()
            return retrofit.create(PhotoService::class.java)
        }
    }
}