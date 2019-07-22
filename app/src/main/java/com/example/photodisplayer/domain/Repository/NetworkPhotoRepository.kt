package com.example.photodisplayer.model

import android.content.Context
import com.example.photodisplayer.domain.Repository.PhotoRepository
import com.example.photodisplayer.domain.Service.PhotoService
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NetworkPhotoRepository: PhotoRepository {
    private var mRetrofit: Retrofit? = null

    val photoApiServe by lazy {
        PhotoService.create()
    }

    private fun getRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            mRetrofit = Retrofit.Builder().baseUrl("http://bechdeltest.com/api/v1/").addConverterFactory(
                GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(client).build()
        }
        return mRetrofit
    }

    override fun searchPhoto(keyword: String, perPage: Int, page: Int): Single<PhotoSearchDTO.Photos> {

        return photoApiServe.searchPhoto(keyword, perPage, page)
    }
}

