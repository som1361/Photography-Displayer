package com.example.webviewscreenshot.utils

import android.graphics.Bitmap
import android.view.View
import android.widget.ProgressBar
import android.graphics.Canvas

fun ProgressBar.show(){
    this.visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    this.visibility = View.GONE
}

