package com.example.myapplication.ui.invoice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CustomLayoutInvoiceBinding
import com.example.myapplication.model.invoice.InvoiceModel

class InvoiceAdapter : ListAdapter<InvoiceModel, InvoiceAdapter.InvoiceHolder>(diffUtil){

    inner class InvoiceHolder(private val view: CustomLayoutInvoiceBinding) :
        RecyclerView.ViewHolder(view.root) {

            fun binding(invoice: InvoiceModel){
                view.invoiceData = invoice
            }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<InvoiceModel>() {
            override fun areItemsTheSame(
                oldItem: InvoiceModel,
                newItem: InvoiceModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: InvoiceModel,
                newItem: InvoiceModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceHolder {
        val view =
            CustomLayoutInvoiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InvoiceHolder(view)
    }

    override fun onBindViewHolder(holder: InvoiceHolder, position: Int) {
        holder.binding(getItem(position))
    }
}