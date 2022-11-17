package com.amar.sitambol.ui.bengkel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import com.amar.sitambol.R
import com.amar.sitambol.data.model.gambar.DataGambar
import com.amar.sitambol.util.GlideHelper

class BengkelSliderAdapter(var context: Context, private var listGambar: ArrayList<DataGambar>)
    : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun getCount(): Int {
        return listGambar.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.adapter_slider, container, false)

        val imageView: ImageView = view.findViewById(R.id.image)

        val gambar = listGambar[position].gambar

        GlideHelper.setImage(context, gambar!!, imageView)
        container.addView(view, 0)

        return view
    }

    override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
        container.removeView(`object` as View)
    }
}