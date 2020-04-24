package com.gabrielrojas.example.exc3

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.properties.Delegates

//class RecyclerExampleAdapter(val items: List<MediaItem>, val OnClick:(MediaItem) -> Unit) : RecyclerView.Adapter<RecyclerExampleHolder>() {

typealias Listener = (MediaItem) -> Unit // creamos un typealias para simplificar la definicion de nuestras variables

// cambiamos el constructor para utilizar delegados
class RecyclerExampleAdapter(val OnClick: Listener) : RecyclerView.Adapter<RecyclerExampleHolder>() {

    // construimos a un delegado observable para identificar si se modifica la lista actualizar el adapter
    var items: List<MediaItem> by Delegates.observable(emptyList()) { p, old, new ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerExampleHolder {
    /*val view : View = LayoutInflater.from(parent.context).inflate(R.layout.h_list, parent, false)
    return RecyclerExampleHolder(view)*/

    // sustituimos por funcion de extension parentde ViewGroup
    return RecyclerExampleHolder(parent.inflate(R.layout.h_list))
}

override fun onBindViewHolder(holder: RecyclerExampleHolder, position: Int) {
    holder.toast("item no: ${position + 1}") // funcion de extension de toast sobre el viewholder
    holder.bind(items[position])

    holder.itemView.setOnClickListener {OnClick(items[position])}
}

override fun getItemCount(): Int = items.size

}