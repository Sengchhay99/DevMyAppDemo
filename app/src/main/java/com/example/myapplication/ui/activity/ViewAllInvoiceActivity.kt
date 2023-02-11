package com.example.myapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.callback.OnItemClick
import com.example.myapplication.databinding.ActivityViewAllInvoiceBinding
import com.example.myapplication.model.PaymentOrderModel
import com.example.myapplication.ui.invoice.DetailInvoiceActivity
import com.example.myapplication.ui.mainActivity.ListOrderAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ViewAllInvoiceActivity : AppCompatActivity() {

    private val binding : ActivityViewAllInvoiceBinding by viewBinding()
    private var actionClick = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()

        val adapter = ListOrderAdapter(callback)

        if (actionClick == "invoice"){
            adapter.submitList(paymentOrderModelList)
        } else {
            adapter.submitList(activityModelList)
        }

        binding.clickBackView = callBackOnClickBack

        binding.recyclerViewInvoice.adapter = adapter

    }

    private fun initData(){
        if (intent != null && intent.hasExtra("action_button")){
            actionClick = intent.getStringExtra("action_button") as String
        }
    }

    private val callBackOnClickBack = object : ClickBackView {
        override fun onClickBackView() {
            finish()
        }

        override fun onClickFilter() {
            val items = arrayOf("All", "Pending", "paid")
            MaterialAlertDialogBuilder(this@ViewAllInvoiceActivity)
                .setTitle(getString(R.string.filter))
                .setItems(items){_, _->

                }.show()
        }

    }
    private val callback = object : OnItemClick<PaymentOrderModel> {
        override fun onClickItem(item: PaymentOrderModel) {
            val intent = Intent(this@ViewAllInvoiceActivity, DetailInvoiceActivity::class.java)
            startActivity(intent)
        }
    }

    private val paymentOrderModelList = listOf(
        PaymentOrderModel(
            id = 1,
            name = "Order #29293",
            date = "12-Jan-2023",
            price = " $1299.00",
            status = "pending",
            iconImage = R.drawable.ic_invoice
        ),
        PaymentOrderModel(
            id = 2,
            name = "Order #3478473",
            date = "22-Jan-2023",
            price = " $3322.00",
            status = "new",
            iconImage = R.drawable.ic_invoice
        ),
        PaymentOrderModel(
            id = 3,
            name = "Order #237847",
            date = "15-Jan-2023",
            price = " $12399.00",
            status = "pending",
            iconImage = R.drawable.ic_invoice
        )
    )

    private val activityModelList = listOf(
        PaymentOrderModel(
            id = 1,
            name = "Chak Anggrae Leu, Phnom penh",
            date = "Today, 2:34pm",
            price = "",
            status = "",
            iconImage = R.drawable.ic_location
        ),
        PaymentOrderModel(
            id = 2,
            name = "Chak Anggrae Leu, Phnom penh",
            date = "Today, 4:34pm",
            price = "",
            status = "",
            iconImage = R.drawable.ic_location
        ),
        PaymentOrderModel(
            id = 3,
            name = "Chak Anggrae Leu, Phnom penh",
            date = "Today, 5:34pm",
            price = "",
            status = "",
            iconImage = R.drawable.ic_location
        )
    )


    interface ClickBackView{
        fun onClickBackView()
        fun onClickFilter()
    }
}