package com.example.webviewscreenshot.utils

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatActivity.hideSoftKeyboard(){
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}