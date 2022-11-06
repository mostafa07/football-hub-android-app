package com.mx3.footballhub.ui.databinding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.mx3.footballhub.R
import java.io.File

@BindingAdapter("imageResource")
fun setImage(imageView: ImageView, imageUri: Uri?) {
    loadImage(imageView, imageUri)
}

@BindingAdapter("imageResource")
fun setImage(imageView: ImageView, imageUrl: String?) {
    loadImage(imageView, imageUrl)
}

@BindingAdapter("imageResource")
fun setImage(imageView: ImageView, imageFile: File?) {
    loadImage(imageView, imageFile)
}

@BindingAdapter("imageResource")
fun setImage(imageView: ImageView, imageResourceId: Int) {
    loadImage(imageView, imageResourceId)
}

@BindingAdapter("imageResource")
fun setImage(imageView: ImageView, imageDrawable: Drawable?) {
    loadImage(imageView, imageDrawable)
}


private fun loadImage(imageView: ImageView, data: Any?) {
    val imageLoader = ImageLoader.Builder(imageView.context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    imageView.load(data = data, imageLoader = imageLoader) {
        placeholder(R.drawable.ic_image)
        error(R.drawable.football)
        crossfade(500)
    }
}