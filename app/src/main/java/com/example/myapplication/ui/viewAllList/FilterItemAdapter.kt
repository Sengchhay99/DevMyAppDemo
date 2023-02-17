package com.example.myapplication.ui.viewAllList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.callback.OnItemClick
import com.example.myapplication.databinding.CustomLayoutFilterBinding

class FilterItemAdapter (private val callbackFilter : OnItemClick<String>? = null): ListAdapter<String, FilterItemAdapter.FilterHolder>(diffUtil){

    var selectPosition = 0

    inner class FilterHolder(private val view: CustomLayoutFilterBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(text : String, position: Int){
            view.itemValue= text
            view.clickAction = callbackFilter
            view.iconCheck.isVisible = selectPosition == position
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
              return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        val view = CustomLayoutFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterHolder(view)
    }

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
       holder.bind(getItem(position), position)
    }
}