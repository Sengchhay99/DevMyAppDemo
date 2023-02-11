package com.example.myapplication.helper

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.myapplication.R
import java.io.File

object Binding {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun setImage(imageView: ImageView, image: Int) {
        imageView.load(image)
    }

    @BindingAdapter("loadImageFile")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, file: File?) {
        imageView.load(file) {
            placeholder(R.color.gray)
            fallback(R.color.gray)
            error(R.color.gray)
        }
    }

    @BindingAdapter("statusColor")
    @JvmStatic
    fun setColorBackgroundStatus(statusText: ImageView, statusKey: String) {
        statusText.imageTintList =
            when(statusKey) {
                "pending" ->
                    ColorStateList.valueOf(ContextCompat.getColor(statusText.context, R.color.yellow))
                "new" ->
                    ColorStateList.valueOf(ContextCompat.getColor(statusText.context, R.color.color_green))

                else -> null
            }

    }

}