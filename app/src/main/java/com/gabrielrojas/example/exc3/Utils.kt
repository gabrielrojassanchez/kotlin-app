package com.gabrielrojas.example.exc3

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

/**
 * funcion de extension sobre viewholder para mostrar un toast
 */
fun RecyclerView.ViewHolder.toast(message: String) {
    itemView.context.toast(message)
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
/**
 * funcion de extension sobre viewgroup para inflar una vista y devolverla
 */
fun ViewGroup.inflate(res: Int) : View {
    return LayoutInflater.from(context).inflate(res, this, false)
}

/**
 * funcion de extension sobre imageview para cargar una imagen con picasso
 */
fun ImageView.loadUrl(url: String) {
    Picasso.get().load(url).fit().centerInside().placeholder(R.mipmap.ic_launcher).into(this)
}

/**
 * funcion para crear una lista con 10 items de MediaItem
 */

fun createMediaList() : List<MediaItem> {
    val items: ArrayList<MediaItem> = ArrayList()

    for(x in 1..10) {

        val t: Int = x % 2 // obtener el residuo de la division

        val item = MediaItem(
                x,
                message = "Android item no ${x}",
                url = thumbBase + x,
                type = if(t == 0) {
                    MediaItem.Type.PHOTO // si es par entonces sera foto
                } else {
                    MediaItem.Type.VIDEO // si es inpar entonces sera vide
                })

        items.add(item)
    }
    return items
}

/**
 * funcion que devuelve true cuando se ha ejecutado una funcion
 */
inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

/**
 * funcion para ordenar una lista de mediaitems a través de un tipo
 */
fun sortList(list: List<MediaItem>, type: MediaItem.Type) : List<MediaItem> {
    return list
            .filter { it.type == type }
            //.sortedBy { it.message }
}

/**
 * funcion para testear otras funcionalidades
 */
fun test() {
    /** test de funcion infix **/
    9.sum(10) // nomenclatura normal de extension

    9 sum 10 // nomenclatura infix
}

/**
 * funcion extendida de Int tipo infix
 */
infix fun Int.sum(valor: Int) = this + valor
