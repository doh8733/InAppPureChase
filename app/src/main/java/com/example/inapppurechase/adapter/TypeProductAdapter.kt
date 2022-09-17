package com.example.inapppurechase.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Guideline
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inapppurechase.MainActivity
import com.example.inapppurechase.ProductActivity
import com.example.inapppurechase.R
import com.example.inapppurechase.model.TypeProduct

class TypeProductAdapter(
    private val context: MainActivity,
    private val listTyperProduct: List<TypeProduct> = mutableListOf()
) : RecyclerView.Adapter<TypeProductAdapter.TypeProductViewHolder>() {
    class TypeProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView by lazy { view.findViewById<ImageView>(R.id.imageView) }
        val tvTypeProduct: TextView by lazy { view.findViewById<TextView>(R.id.tvTypeProduct) }
         val layout: CardView by lazy { view.findViewById<CardView>(R.id.layout) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeProductViewHolder {
        return TypeProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_type_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TypeProductViewHolder, position: Int) {
        val item = listTyperProduct[position]
        Glide.with(context).load(item.image).into(holder.imageView)
        holder.tvTypeProduct .text = item.type
        holder.layout.setOnClickListener {
            val id : Int = item.id
            val image : Int = item.image
            val type : String = item.type
            val i = Intent(context,ProductActivity::class.java)
            i.putExtra("id",id)
            i.putExtra("image",image)
            i.putExtra("type",type)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return listTyperProduct.size
    }
}