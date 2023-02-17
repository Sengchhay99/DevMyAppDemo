package com.example.myapplication.ui.viewAllList

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.callback.OnItemClick
import com.example.myapplication.databinding.ActivityViewAllInvoiceBinding
import com.example.myapplication.databinding.DialogLayoutViewListFilterBinding
import com.example.myapplication.model.PaymentOrderModel
import com.example.myapplication.ui.invoice.DetailInvoiceActivity
import com.example.myapplication.ui.mainActivity.ListOrderAdapter
import com.kongzue.dialogx.dialogs.BottomDialog
import com.kongzue.dialogx.interfaces.OnBindView
import com.paginate.Paginate
import java.util.concurrent.TimeUnit

class ViewAllInvoiceActivity : AppCompatActivity() {

    private val binding: ActivityViewAllInvoiceBinding by viewBinding()
    private var actionClick = ""
    private var position = 0
    private var isLoadingView = false
    private var isAllItem = false
    private var currentPage: Int = 1

    private val adapter = ListOrderAdapter(callback())

    val itemList = listOf(
        "All",
        "Pending",
        "Unpaid"
    )

    val activityList = listOf(
        "Today",
        "Yesterday",
        "This Week",
        "Last Week",
        "This Month",
        "Last Month",
        "This Year",
        "Last year"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
//
//        if (actionClick == "invoice") {
////            adapter.submitList(paymentOrderModelList)
//        } else {
////            adapter.submitList(activityModelList)
//        }

        binding.recyclerViewInvoice.adapter = adapter

        loadingPaginate()
        binding.clickBackView = callBackOnClickBack

    }

    private fun initData() {
        if (intent != null && intent.hasExtra("action_button")) {
            actionClick = intent.getStringExtra("action_button") as String
        }
    }

    private fun loadingPaginate() {
        Paginate.with(binding.recyclerViewInvoice, callbackPaginate)
            .setLoadingTriggerThreshold(10      )
            .addLoadingListItem(false)
            .build()
    }

    private val callbackPaginate = object : Paginate.Callbacks {
        override fun onLoadMore() {
            isLoadingView = true
            binding.isRefresh = true

            Handler(Looper.getMainLooper()).postDelayed({
                adapter.submitList(adapter.currentList + paymentOrderModelList) {
                    currentPage++
                    isLoadingView = false
                    binding.isRefresh = false
                    isAllItem = currentPage == 5
                }
            }, 1000)
        }

        override fun isLoading(): Boolean {
            return isLoadingView
        }

        override fun hasLoadedAllItems(): Boolean {
            return isAllItem
        }

    }

    private val callBackOnClickBack = object : ClickBackView {
        override fun onClickBackView() {
            finish()
        }

        override fun onClickFilter() {
            BottomDialog.build()
                .setCustomView(object :
                    OnBindView<BottomDialog>(R.layout.dialog_layout_view_list_filter) {
                    override fun onBind(dialog: BottomDialog?, v: View?) {
                        v ?: return
                        val binding = DialogLayoutViewListFilterBinding.bind(v)
                        val adapter = FilterItemAdapter(callbackFilter(dialog))
                        adapter.selectPosition = position
                        binding.recyclerViewFilter.adapter = adapter
                        if (actionClick == "invoice") {
                            adapter.submitList(itemList)
                        } else {
                            adapter.submitList(activityList)
                        }

                    }
                })
                .setAllowInterceptTouch(true)
                .setCancelable(true)
                .show(this@ViewAllInvoiceActivity)
        }
    }

    private fun callbackFilter(dialog: BottomDialog?) = object : OnItemClick<String> {
        override fun onClickItem(item: String) {

            binding.iconAllTitle.text = item

            val itemPos = itemList.indexOf(item)
            position = itemPos
            dialog?.dismiss()
        }

    }

    private fun callback(): OnItemClick<PaymentOrderModel> =
        object : OnItemClick<PaymentOrderModel> {
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
        ),
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
        ),
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
        ),
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
        ),
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
        ),
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
        ),
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


    interface ClickBackView {
        fun onClickBackView()
        fun onClickFilter()
    }
}