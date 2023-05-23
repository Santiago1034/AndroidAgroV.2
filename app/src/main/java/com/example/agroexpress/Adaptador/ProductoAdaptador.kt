package com.example.agroexpress.Adaptador

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agroexpress.Modelos.Producto
import com.example.agroexpress.R
import org.json.JSONArray
import org.json.JSONObject

class ProductoAdaptador (private val ProductoList: ArrayList<JSONObject>, private val itemListener: ItemListener) : RecyclerView.Adapter<ProductoAdaptador.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var imagen: ImageView = view.findViewById(R.id.tvimg_producto)
        var nombre: TextView = view.findViewById(R.id.tvnombre_producto)

        fun bind(Producto: JSONObject){
            nombre.text = Producto.getString("LisP_Nombre")
            Log.d("json",Producto.toString())

        }
    }
    override  fun  onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_producto,parent,false)
    )
    override fun getItemCount() = this.ProductoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = ProductoList[position]

        try{
            Glide.with(holder.itemView.context)
                .load(product.get("LisP_UrlImg"))
                .into(holder.imagen)
            holder.bind(product)

            holder.itemView.setOnClickListener{
                itemListener.onItemClicked(product,position)
            }

        }catch (e : Exception){
            Log.w("Productosdatos", "No cargan datos")
        }
    }


}

