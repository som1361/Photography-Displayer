package com.example.photodisplayer.viewmodel

import android.content.Context
import com.example.photodisplayer.application.PhotoApplication
import com.example.photodisplayer.domain.Repository.PhotoRepository
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException
import javax.inject.Inject

class MainViewModel @Inject constructor(private var photoRepository: PhotoRepository){
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun findPhotos(keyword: String, perPage: Int, page: Int)  {
        val disposable: Disposable = photoRepository.searchPhoto(keyword, perPage, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<PhotoSearchDTO.Photos>(){
                override fun onSuccess(t: PhotoSearchDTO.Photos) {
                    PhotoApplication.getAsyncComponent().getGetContentObservable().onNext(t)
                }

                override fun onError(e: Throwable) {
                    PhotoApplication.getAsyncComponent().getGetContentErrorObservable().onNext(e as HttpException)
                }

            })
        compositeDisposable.add(disposable)
    }

    fun cancelNetworkConnections() {
        compositeDisposable.clear()
    }
}
