package com.gabrielrojas.example.exc3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import org.jetbrains.anko.startActivity

// utilizar import sintetico para android-kotlin-extensions
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // utilizaremos el delegado lazy para inicializar el recycler y lo hace cuando se llama a la variable
    val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_list)
    }

    val adapter = RecyclerExampleAdapter { (id) ->
        //applicationContext.toast("Click en ${mediaItem.message}")
        navigateToDetail(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val rvList : RecyclerView = findViewById(R.id.rv_list)

        // utilizamos android-kotlin-extensions para obtener el recycler directamente

        /*rv_list.adapter = RecyclerExampleAdapter(createMediaList()) {
            mediaItem ->  applicationContext.toast("Click en ${mediaItem.message}")
        }*/

        // aplicamos un lambda en lugar de un listener con interfaces


        recyclerView.adapter = adapter
        //adapter.items = GetMedia.mediaItems

        // obtenemos la lista a través de un proceso asincrono
        GetMedia.getMedias {
            medias -> adapter.items = medias
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }


    // obtenemos la lista a través de un proceso asincrono

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*GetMedia.getMedias { medias ->

            when (item.itemId) {
                R.id.action_all -> consume {
                    adapter.items = medias
                }
                R.id.action_photo -> consume {
                    adapter.items = sortList(medias,  MediaItem.Type.PHOTO)
                }
                R.id.action_video -> consume {
                    adapter.items = sortList(medias, MediaItem.Type.VIDEO)
                }
                else -> consume {emptyList<MediaItem>()}
            }
        }*/

        // comparamos las opciones seleccionadas devolvien            R.id.action_video -> Filter.ByType(MediaItem.Type.VIDEO)do objetos de tipo filtro
        val filter: Filter = when (item.itemId) {
            R.id.action_photo -> Filter.ByType(MediaItem.Type.PHOTO)
            R.id.action_video -> Filter.ByType(MediaItem.Type.VIDEO)
            else -> Filter.None
        }

        loadFilterData(filter)
        return true
    }

    private fun navigateToDetail(id: Int) {
        //utilizaremos el startactivity de anko mandando un Pair de parametro con la funcion infix 'to'
        startActivity<DetailActivity>(DetailActivity.ID to id)
    }

    /**
     * funcion que recibe un filtro y este es buscado dependiendo de la seleccion despues de haber
     * obtenido los datos de manera asincrona y el resultado sera asignado a los items del adapter
     */
    private fun loadFilterData(filter: MainActivity.Filter) {
        GetMedia.getMedias { medias ->
            adapter.items = when (filter) {
                Filter.None -> medias
                is Filter.ByType -> medias.filter { it.type == filter.type}
            }
        }
    }

    // creamos una clase sellada con los diferentes tipos de filtros
    sealed class Filter {
        object None : Filter() // este elemento puede ser object ya que no requiere estado (constructor vacio)
        class ByType (val type: MediaItem.Type) : Filter()
    }
}
