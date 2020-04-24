package com.gabrielrojas.example.exc3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ID : String = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id: Int = intent.getIntExtra(ID, 0)

        GetMedia.getMedias { medias ->
            // utilizamos let para comparar si el item no es nulo hara la asignacion de los valores
             medias.find { it.id == id }?.let { item ->
                 // si supportactionbar no es nulo asignara un titulo
                 supportActionBar?.title = item.message
                 detail_thumb.loadUrl(item.url)
                 detail_video_indicator.visibility = when (item.type) {

                     MediaItem.Type.PHOTO -> View.GONE
                     MediaItem.Type.VIDEO -> View.VISIBLE
                 }
             }
        }
    }
}
