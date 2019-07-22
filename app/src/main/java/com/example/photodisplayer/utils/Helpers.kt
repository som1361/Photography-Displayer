package com.example.webviewscreenshot.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun showSuccessMessage(context: Context, message: Int) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast.view.setBackgroundColor(Color.GRAY)
    toast.setGravity(Gravity.BOTTOM, 0, 0);
    toast.show()
}

fun showFailMessage(context: Context, message: Int) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast.view.setBackgroundColor(Color.RED)
    toast.setGravity(Gravity.BOTTOM, 0, 0);
    toast.show()
}