package com.gabrielrojas.example.exc3

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.h_list.view.*

class RecyclerExampleHolder (itemView: View): RecyclerView.ViewHolder (itemView) {

    fun bind(item: MediaItem){
        /*
        val ivPic: ImageView = itemView.findViewById(R.id.iv_pic)
        val ivVid: ImageView = itemView.findViewById(R.id.iv_video)
        val tvList  : TextView  = itemView.findViewById(R.id.tv_list)
        */
        // TODO utilizamos android-kotlin-extensions para obtener view del xml diretamente

        with(itemView) { // utilizamos with para simplificar la obtencion de los views como: itemView.iv_video
            tv_list.text = item.message

            /*Picasso.get()
                    .load(item.url)
                    .fit()
                    .into(ivPic)*/

            //sustituimos por funcion de extension
            iv_pic.loadUrl(item.url)

            // usamos condicional when para comparar la visibilidad a tener
            iv_video.visibility = when(item.type) {
                MediaItem.Type.PHOTO -> View.INVISIBLE
                MediaItem.Type.VIDEO -> View.VISIBLE
        }


            /**
             * TODO Nota:
             * el uso de kotin-extensions simplifica la obtencion de la vista pero
             * como esto se esta haciendo dentro de un bind, el holder no tiene como
             * obtener las vistas y siempre utilza el metodo bind y siempre por detras
             * de las android-extensions siempre se manda llamar a findviewbyid, por lo que
             * se recomienda usar en los holders de la lista iniciar las vistas como globales
             * y no en el metodo bind ya que afectaria su rendimiento con vistas mas complejas,
             *
             *esto no pasa en lugares como la activity ya que solo hace el findviewbyid una sola vez
             */
        }
    }
}