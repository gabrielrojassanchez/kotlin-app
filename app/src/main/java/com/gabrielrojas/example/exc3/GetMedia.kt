package com.gabrielrojas.example.exc3

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * object es equivalente a unsigleton en java pero más seguro
 * en este caso crearemos un object para obtener una lista de 10 elementos de MediaItem
 */
object GetMedia {
    /*val mediaItems = (1..10).map {
        MediaItem(message = "Android item no $it",
                url = thumbBase + it,
                type = if (it % 2 == 0) {
                    MediaItem.Type.PHOTO // si es par entonces sera foto
                } else {
                    MediaItem.Type.VIDEO // si es inpar entonces sera vide
                })}*/


    var mediaItems : List<MediaItem> = emptyList()

    /**
     * funcion que recibe un lambda para el callback y utiliza un proceso asincrono
     * creado con la libreria anko
     */
    fun getMedias(callback:(List<MediaItem>) -> Unit) {
        if(mediaItems.isEmpty()) { // si esta vacia entonces que llene la lista si no usara cache
            doAsync {
                Thread.sleep(3000)
                mediaItems = (1..10).map {
                    MediaItem(id = it,
                            message = "Android item no $it",
                            url = thumbBase + it,
                            type = if (it % 2 == 0) {
                                MediaItem.Type.PHOTO // si es par entonces sera foto
                            } else {
                                MediaItem.Type.VIDEO // si es inpar entonces sera vide
                            })
                }

                uiThread {
                    callback(mediaItems)
                }
            }

        } else {
             callback(mediaItems)
        }
    }

}