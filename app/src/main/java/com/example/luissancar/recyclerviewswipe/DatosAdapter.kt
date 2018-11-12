package com.example.luissancar.recyclerviewswipe


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_datos.view.*


import com.squareup.picasso.Picasso


class DatosAdapter(val items : ArrayList<registro>, val context: Context) : RecyclerView.Adapter<ViewHolder>(){

    // Obtiene el número de datos
    override fun getItemCount(): Int {
        return items.size
    }

    //infla el layout activity_datos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_datos, parent, false))



    }




    // carga datos del ArrayList aL TEXTVIEW view


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvDatosA?.text = items.get(position).dato1

        holder?.tvDatosB?.text = items.get(position).dato2
        holder?.ivDatos?.loadUrl(items.get(position).url)

        holder?.itemView?.setOnClickListener(View.OnClickListener { Toast.makeText(context,  items.get(position).dato1, Toast.LENGTH_SHORT).show() })



    }
    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Mantiene el TextView
    val tvDatosA = view.textViewDatos
    val tvDatosB = view.textViewDatos2
    val ivDatos= view.ivDato



}