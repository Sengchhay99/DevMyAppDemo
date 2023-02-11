package com.example.myapplication.ui.invoice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.callback.OnItemClick
import com.example.myapplication.databinding.FragmentInvoiceBinding
import com.example.myapplication.model.PaymentOrderModel
import com.example.myapplication.model.ProfileModel
import com.example.myapplication.ui.activity.ViewAllInvoiceActivity
import com.example.myapplication.ui.mainActivity.ListOrderAdapter

class InvoiceFragment : Fragment(R.layout.fragment_invoice) {

    private val binding : FragmentInvoiceBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListOrderAdapter(callback)

        binding.clickButtonFragment = callbackBtnView
        binding.recyclerOrder.adapter = adapter

        adapter.submitList(paymentOrderModelList)

        val profileData = ProfileModel(1, R.drawable.ic_profile, "")
        binding.imageProfile = profileData

    }

    private val callback = object : OnItemClick<PaymentOrderModel> {
        override fun onClickItem(item: PaymentOrderModel) {
            val intent = Intent(context, DetailInvoiceActivity::class.java)
            startActivity(intent)
        }
    }

    private val callbackBtnView = object : MainEventOrder {
        override fun onClickButtonView() {
            val intent = Intent(context, ViewAllInvoiceActivity::class.java)
            intent.putExtra("action_button", "invoice")
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

    interface MainEventOrder{
        fun onClickButtonView()
    }
}