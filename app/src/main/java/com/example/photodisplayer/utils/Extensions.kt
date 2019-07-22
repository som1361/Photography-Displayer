package com.example.webviewscreenshot.utils

import android.graphics.Bitmap
import android.view.View
import android.widget.ProgressBar
import android.graphics.Canvas
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.widget.TextView

fun ProgressBar.show(){
    this.visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    this.visibility = View.GONE
}

fun SpannableString.withClickableSpan(clickablePart: String, onClickListener: () -> Unit): SpannableString {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View?) = onClickListener.invoke()
    }
    val clickablePartStart = indexOf(clickablePart)
    setSpan(clickableSpan,
        clickablePartStart,
        clickablePartStart + clickablePart.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun TextView.makePartOfTextViewClickable(clickablePart: String, onClickListener: () -> Unit): SpannableString {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View?) = onClickListener.invoke()
    }
    val spannableString = SpannableString(text)

    val startIndexOfLink = text.toString().indexOf(clickablePart)
    spannableString.setSpan(
        clickableSpan, startIndexOfLink, startIndexOfLink + clickablePart
            .length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    movementMethod = LinkMovementMethod.getInstance()
    setText(spannableString, TextView.BufferType.SPANNABLE)
    return spannableString
}

