package com.example.photodisplayer.viewmodel

import com.example.photodisplayer.domain.model.PhotoSearchDTO
import com.example.photodisplayer.model.PhotoSearchModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException

class MainViewModel (){
    lateinit var resultObservable: PublishSubject<PhotoSearchDTO.Photos>
    lateinit var resultErrorObservable: PublishSubject<HttpException>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var organicModel:PhotoSearchModel

    constructor(mOrganicModel:PhotoSearchModel):this(){
        organicModel = mOrganicModel
        resultObservable = PublishSubject.create()
        resultErrorObservable = PublishSubject.create()
    }

    fun findPhotos(keyword: String)  {
        val disposable: Disposable = organicModel.fetchOrganicList(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<PhotoSearchDTO.Photos>(){
                override fun onSuccess(t: PhotoSearchDTO.Photos) {
                    resultObservable.onNext(t)
                }

                override fun onError(e: Throwable) {
                    resultErrorObservable.onNext(e as HttpException)
                }

            })
        compositeDisposable.add(disposable)
    }
}
