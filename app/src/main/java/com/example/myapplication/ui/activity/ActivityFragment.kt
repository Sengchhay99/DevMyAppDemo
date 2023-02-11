package com.example.myapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.callback.OnItemClick
import com.example.myapplication.databinding.FragmentActivityBinding
import com.example.myapplication.model.PaymentOrderModel
import com.example.myapplication.model.ProfileModel
import com.example.myapplication.ui.mainActivity.ListOrderAdapter


class ActivityFragment : Fragment(R.layout.fragment_activity) {

    private val binding : FragmentActivityBinding by viewBinding()

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
            Log.e("","onClickItem: $item")
        }
    }

    private val callbackBtnView = object : MainEventOrder {
        override fun onClickButtonView() {
            val intent = Intent(context, ViewAllInvoiceActivity::class.java)
            startActivity(intent)
        }

        override fun onClickCreateActivity() {
            val intent = Intent(context, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    private val paymentOrderModelList = listOf(
        PaymentOrderModel(
            id = 1,
            name = "Chak Anggrae Leu, Phnom Penh",
            date = "Today, 2:34pm",
            price = "",
            status = "",
            iconImage = R.drawable.ic_location
        ),
        PaymentOrderModel(
            id = 2,
            name = "Chak Anggrae Leu, Phnom Penh",
            date = "Today, 3:00pm",
            price = "",
            status = "",
            iconImage = R.drawable.ic_location
        ),
        PaymentOrderModel(
            id = 3,
            name = "Chak Anggrae Leu, Phnom Penh",
            date = "Today, 4:14pm",
            price = "",
            status = "",
            iconImage = R.drawable.ic_location
        )
    )

    interface MainEventOrder{
        fun onClickButtonView()

        fun onClickCreateActivity()
    }
}