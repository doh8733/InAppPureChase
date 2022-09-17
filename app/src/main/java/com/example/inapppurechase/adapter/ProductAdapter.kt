package com.example.inapppurechase.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inapppurechase.ProductActivity
import com.example.inapppurechase.R
import com.example.inapppurechase.ShowDeteilProductActivity
import com.example.inapppurechase.model.Product

class ProductAdapter(
    private val context: ProductActivity,
    private val listProduct : List<Product>,
    private val callback : (Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(view :View): RecyclerView.ViewHolder(view){
         val imageView2: ImageView by lazy { view.findViewById<ImageView>(R.id.imageView2) }
         val tvName: TextView by lazy { view.findViewById<TextView>(R.id.tvName) }
         val tvPrice: TextView by lazy { view.findViewById<TextView>(R.id.tvPrice) }
         val layout: CardView by lazy { view.findViewById<CardView>(R.id.layout) }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = listProduct[position]

        holder.itemView.setOnClickListener {
            callback.invoke(position)
        }

        Glide.with(context).load(item.image).into(holder.imageView2)
        holder.tvName.text = item.name
        holder.tvPrice.text = item.price.toString()
//        holder.layout.setOnClickListener {
//            val id :Int = item.id
//            val image :String = item.image
//            val type :String = item.type
//            val name :String = item.name
//            val price :String = item.price.toString()
//            val i = Intent(context,ShowDeteilProductActivity::class.java)
//            i.putExtra("id",id)
//            i.putExtra("image",image)
//            i.putExtra("type",type)
//            i.putExtra("name",id)
//            i.putExtra("price",price)
//            context.startActivity(i)
//        }
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }
}