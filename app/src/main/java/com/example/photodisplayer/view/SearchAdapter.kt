package com.example.photodisplayer.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.photodisplayer.R
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import com.example.photodisplayer.domain.model.PhotoSearchDTO.Photo
import kotlinx.android.synthetic.main.photo_item.view.*

//Make the class extend RecyclerView.ViewHolder, allowing the adapter to use it as as a ViewHolder
class SearchAdapter(val clickListener: PhotoListener) : RecyclerView.Adapter<SearchAdapter.PhotocHolder>() {
    var photos: List<PhotoSearchDTO.Photo> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.PhotocHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.photo_item, parent, false)
        return PhotocHolder(view!!)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: SearchAdapter.PhotocHolder, position: Int) {
        holder.bindPhoto(clickListener, photos[position])
    }

    fun updateList(photoList: List<PhotoSearchDTO.Photo>) {
        photos = photoList

    }

    class PhotocHolder(v: View) : RecyclerView.ViewHolder(v) {
        //Add a reference to the view you’ve inflated to allow the ViewHolder to access the views as an extension property
        private var view: View = v
        private var photo: Photo? = null

        fun bindPhoto(clickListener: PhotoListener, photo: Photo) {
            this.photo = photo
            Glide.with(view.context).load(photo.src.small).into(view.item_image)
            view.photographer_name.text = photo.photographer
            view.photographer_url.text = photo.photographer_url
            view.item_image.setOnClickListener { clickListener.onClick(photo) }
        }
    }
}
class PhotoListener(val clickListener: (photo: String) -> Unit) {
    fun onClick(photo: Photo) = clickListener(photo.url)
}
