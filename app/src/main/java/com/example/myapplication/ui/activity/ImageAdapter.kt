package com.example.myapplication.ui.activity

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CustomLayoutImageBinding
import java.io.File

class ImageAdapter(private val callbackImage: OnItemClickListener? = null) : ListAdapter<File, ImageAdapter.ImageHolder>(diffUtil) {

    inner class ImageHolder(private val view: CustomLayoutImageBinding) :
        RecyclerView.ViewHolder(view.root) {

            fun bind(file: File){
                view.fileImage = file
                view.clickImage = callbackImage
            }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<File>() {
            override fun areItemsTheSame(
                oldItem: File,
                newItem: File
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: File,
                newItem: File
            ): Boolean {
                Log.e("ImageAdapter.kt", "areContentsTheSame:${oldItem.path}/${newItem.path}")
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = CustomLayoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickListener{
        fun viewImage()
        fun removeImage(file: File)
    }
}