package com.example.photodisplayer.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MotionEvent
import android.view.View
import com.example.photodisplayer.DI.component.ActivityComponent
import com.example.photodisplayer.DI.component.DaggerActivityComponent
import com.example.photodisplayer.DI.module.ActivityModule
import com.example.photodisplayer.R
import com.example.photodisplayer.application.PhotoApplication
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import com.example.photodisplayer.viewmodel.MainViewModel
import com.example.webviewscreenshot.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mMainViewModel: MainViewModel
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var mSearchAdapter: SearchAdapter
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = DaggerActivityComponent
            .builder()
            .appComponent((application as PhotoApplication).photoComponent)
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        loadView()
        respondToClicks()
        listenToObservables()
    }

    private fun listenToObservables() {
        PhotoApplication.getAsyncComponent().getGetContentObservable().subscribe({
            search_progress_bar.hide()
            if (it.photos.size == 0)
                showSuccessMessage(this, R.string.empty_list)
            else {
                link_textview.visibility = View.VISIBLE
                updatePhotoList(it)
            }
        })
        PhotoApplication.getAsyncComponent().getGetContentErrorObservable().subscribe({
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
        //Click item to load the photo’s URL in web browser
        mSearchAdapter = SearchAdapter(PhotoListener { photoUrl ->
            startActivity( Intent(Intent.ACTION_VIEW, Uri.parse(photoUrl)))
        })
        mGridLayoutManager = GridLayoutManager(this, 2)
        search_recyclerview.layoutManager = mGridLayoutManager
        search_recyclerview.adapter = mSearchAdapter
        search_progress_bar.hide()

//add prominent link to Pexels on the search result page
        link_textview.makePartOfTextViewClickable("Pexels", { gotoWebViewActivity(getString(R.string.prominent_link)) })
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
            val keyword = search_editText.text.toString()
            if (keyword.length == 0)
                showFailMessage(this, R.string.invalid_keyword)
            else {
                search_progress_bar.show()
                val page = 1
                val perPage = 30
                mMainViewModel.findPhotos(search_editText.text.toString(), perPage, page)
            }
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
