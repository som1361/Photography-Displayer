package com.example.photodisplayer.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MotionEvent
import android.view.View
import com.example.photodisplayer.R
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import com.example.photodisplayer.model.PhotoSearchModel
import com.example.photodisplayer.viewmodel.MainViewModel
import com.example.webviewscreenshot.utils.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var mSearchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainViewModel = MainViewModel(PhotoSearchModel())
        loadView()
        respondToClicks()
        listenToObservables()
    }

    private fun listenToObservables() {
        mMainViewModel.resultObservable.subscribe({
            search_progress_bar.hide()
            if (it.photos.size == 0)
                showSuccessMessage(this, R.string.empty_list)
            updatePhotoList(it)
        })
        mMainViewModel.resultErrorObservable.subscribe({
            search_progress_bar.hide()
          //  showFailMessage(this, it.message())
        })
    }

    private fun updatePhotoList(it: PhotoSearchDTO.Photos?) {
        if (it != null) {
            mSearchAdapter.updateList(it.photos)
            mSearchAdapter.notifyDataSetChanged()

        }
    }

    private fun loadView() {
        setContentView(R.layout.activity_main)
        mSearchAdapter = SearchAdapter(PhotoListener { photoUrl ->
            gotoWebViewActivity(photoUrl)
        })
        mGridLayoutManager = GridLayoutManager(this, 2)
        search_recyclerview.layoutManager = mGridLayoutManager
        search_recyclerview.adapter = mSearchAdapter
        search_progress_bar.hide()
    }

    private fun gotoWebViewActivity(photoUrl: String) {
        val bundle = Bundle()
        bundle.putString(PhotoActivity.Constants.URL, photoUrl)
        val intent = Intent(this, PhotoActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun respondToClicks() {
        search_imageView.setOnClickListener({
            search_progress_bar.show()
            mMainViewModel.findPhotos(search_editText.text.toString())
        })

        search_layout.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                this@MainActivity.hideSoftKeyboard()
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mMainViewModel.cancelNetworkConnections()
    }
}
