package com.example.myapplication.ui.mainActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.callback.OnItemClick
import com.example.myapplication.databinding.CutomLayoutOrderListBinding
import com.example.myapplication.model.PaymentOrderModel

class ListOrderAdapter(private val callback: OnItemClick<PaymentOrderModel>? = null) :
    ListAdapter<PaymentOrderModel, ViewHolder>(diffUtil) {

    inner class ViewHolder(private val view: CutomLayoutOrderListBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(data: PaymentOrderModel) {
            view.order = data
            view.click = callback
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PaymentOrderModel>() {
            override fun areItemsTheSame(
                oldItem: PaymentOrderModel,
                newItem: PaymentOrderModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PaymentOrderModel,
                newItem: PaymentOrderModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            CutomLayoutOrderListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }
}