package com.mazaira.searchtv.util.extensions

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mazaira.searchtv.R
import com.squareup.picasso.Picasso

@Deprecated("Excesivo consumo de CPU o.0", ReplaceWith("loadImageWithGlide"))
fun ImageView.loadImage(url:String?) {

    url?.let {
        Picasso.get()
            .load(Uri.parse(url))
            .placeholder(R.drawable.sample_movie)
            .centerCrop()
            .into(this)
    }
}

fun ImageView.loadImageWithGlide(url: String?){
    url?.let {
        Glide.with(this.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .centerCrop()
            .into(this)
    }


}