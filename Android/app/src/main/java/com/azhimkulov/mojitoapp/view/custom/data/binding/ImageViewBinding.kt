package com.azhimkulov.mojitoapp.view.custom.data.binding

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import com.azhimkulov.mojitoapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


@BindingAdapter("app:url")
fun loadUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .into(imageView)

}

@BindingAdapter("app:loadWithProgress")
fun loadUrl(container:ViewGroup, url: String?) {
    val imageView = container.children.toMutableList()[0] as ImageView
    val progressBar = container.children.toMutableList()[1] as ProgressBar


    progressBar.visibility = View.VISIBLE

    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .listener(object:RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }

        })
        .into(imageView)
}

@BindingAdapter("app:favoriteIcon")
fun setFavoriteIcon(imageView: ImageView, isFavorite:Boolean) {
    if (isFavorite) {
        imageView.setImageResource(R.drawable.ic_favorited)
    } else {
        imageView.setImageResource(R.drawable.ic_favorite)
    }
}
