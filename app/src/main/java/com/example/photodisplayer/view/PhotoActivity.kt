package com.example.photodisplayer.view

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.photodisplayer.R
import com.example.webviewscreenshot.utils.hide
import com.example.webviewscreenshot.utils.show
import kotlinx.android.synthetic.main.activity_webview.*

class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadView()
    }

    private fun loadView() {
        setContentView(R.layout.activity_webview)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        val bundle = intent.extras
        val photoUrl = bundle.getString(Constants.URL)
        loadContent(photoUrl)
    }

    private fun loadContent(url: String) {
        photo_webview.getSettings().setJavaScriptEnabled(true)
        photo_webview.getSettings().setDomStorageEnabled(true)
        photo_webview.setWebViewClient(object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    photo_progress_bar.hide()
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    photo_progress_bar.show()
                }

                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            })
            photo_webview.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }

    object Constants {
        const val URL = "url"
    }
}
