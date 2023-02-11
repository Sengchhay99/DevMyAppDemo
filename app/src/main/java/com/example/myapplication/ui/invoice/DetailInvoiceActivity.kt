 package com.example.myapplication.ui.invoice

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailInvoiceBinding
import com.example.myapplication.model.invoice.InvoiceModel

 class DetailInvoiceActivity : AppCompatActivity() {

     private val binding : ActivityDetailInvoiceBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = InvoiceAdapter()
        binding.clickAction = callbackView
        binding.title = getString(R.string.invoice)
        binding.ownerNameText.text = "Heng ChunLong"
        binding.dateInvoiceText.text = "22 Mar, 2021"
        binding.phoneText.text = "012 585949"
        binding.addressText.text = "Chakkk Angrae, phnom penh"
        binding.orderNoText.text = " Order #99383"
        binding.totalText.text = "$ 52"
        binding.recyclerViewInvoice.adapter = adapter

        adapter.submitList(invoiceList)

    }

     private val invoiceList  = listOf(
         InvoiceModel(
             1,
             "Coca cola",
             "12",
             "$1",
             "$12"
         ),
         InvoiceModel(
             2,
             "Pizza company",
             "2",
             "$15",
             "$30"
         ),
         InvoiceModel(
             3,
             "Chicken",
             "10",
             "$1",
             "$10"
         )
     )

     private val callbackView = object : ClickBackView {
         override fun clickBack() {
            finish()
         }
     }

     interface ClickBackView {
         fun clickBack()
     }
}