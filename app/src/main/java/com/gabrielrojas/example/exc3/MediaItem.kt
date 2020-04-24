package com.gabrielrojas.example.exc3

data class MediaItem(val id: Int, val url: String = URL_IMAGE, val message: String, val type: Type = Type.PHOTO) {
    enum class Type {PHOTO, VIDEO} // utilizamos enums en kotlin
}