package com.example.inapppurechase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.inapppurechase.R
import com.example.inapppurechase.model.Photos

class PhotoSliderAdapter(
    private val context: Context,
    private val listPhoto: List<Photos>
) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =
            LayoutInflater.from(container.context).inflate(R.layout.item_photo, container, false)
        val imSlider: ImageView by lazy { view.findViewById<ImageView>(R.id.imSlider) }
        val photo: Photos = listPhoto[position]

        Glide.with(context).load(photo.reSourceId).into(imSlider)
        container.addView(view)
        return view

    }

    override fun getCount(): Int {
        return listPhoto.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}