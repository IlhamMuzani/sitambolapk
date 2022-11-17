package com.amar.sitambol.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.amar.sitambol.R
import com.amar.sitambol.data.Constant
import com.bumptech.glide.Glide
import java.io.File

class GlideHelper {
    companion object {
        fun setImage(context: Context, urlImage: String, imageView: ImageView) {
            Glide.with(context)
                .load(Constant.IP_IMAGE + urlImage)
                .centerCrop()
                .placeholder(R.drawable.icon_bengkel)
                .error(R.drawable.icon_bengkel)
                .into(imageView)
        }
    }

}