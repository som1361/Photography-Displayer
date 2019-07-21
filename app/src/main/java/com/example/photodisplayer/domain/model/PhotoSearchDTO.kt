package com.example.photodisplayer.domain.model

object PhotoSearchDTO {
    data class Photos(val photos: List<Photo>)
    data class Photo(val src: Source, val photographer: String, val url: String, val photographer_url: String)
    data class Source(val small: String)
}