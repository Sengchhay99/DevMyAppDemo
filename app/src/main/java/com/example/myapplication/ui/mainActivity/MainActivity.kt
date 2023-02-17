package com.example.myapplication.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.DialogSetProfileBinding
import com.example.myapplication.model.ProfileModel
import com.example.myapplication.ui.activity.ActivityFragment
import com.example.myapplication.ui.viewAllList.ViewAllInvoiceActivity
import com.example.myapplication.ui.dashboard.DashboardFragment
import com.example.myapplication.ui.invoice.InvoiceFragment
import com.kongzue.dialogx.dialogs.BottomDialog
import com.kongzue.dialogx.interfaces.OnBindView

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private var activeFragment: Fragment = Fragment()
    private var selectPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activeFragment = getFragment(DASHBOARD)

        binding.clickButtonFragment = callbackBtnView

        val profileData = ProfileModel(1, R.drawable.ic_profile, "")
        binding.imageProfile = profileData

        binding.bottomNav.setOnItemSelectedListener {
            binding.invoiceTitle.text = it.title as String
            replaceFragment(it)
            true
        }
    }

    private val callbackBtnView = object : MainEventOrder {
        override fun onClickButtonView() {
            val intent = Intent(this@MainActivity, ViewAllInvoiceActivity::class.java)
            intent.putExtra("action_button", "invoice")
            startActivity(intent)
        }

        override fun onClickProfile() {
            BottomDialog.build()
                .setCustomView(object : OnBindView<BottomDialog>(R.layout.dialog_set_profile) {
                    override fun onBind(dialog: BottomDialog?, v: View?) {
                        v ?: return
                        val binding = DialogSetProfileBinding.bind(v)
                        binding.clickActionProfile = callbackProfile(dialog)
                        val profileData = ProfileModel(1, R.drawable.ic_profile, "Data Type Name")
                        binding.imageProfile = profileData
                    }
                })
                .setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.light_gray))
                .setAllowInterceptTouch(false)
                .setCancelable(false)
                .show(this@MainActivity)
        }
    }

    private fun callbackProfile(bottomDialog: BottomDialog?) = object : CallBackProfile {
        override fun onClickClose() {
            bottomDialog?.dismiss()
        }

        override fun onClickPrinter() {

        }

        override fun onClickChangePassword() {

        }

        override fun onClickLogout() {

        }
    }

    private fun replaceFragment(menuItem: MenuItem) {
        if (selectPosition == menuItem.itemId) return
        when (menuItem.itemId) {
            R.id.dashboard -> {
                swapFragment(getFragment(tag = DASHBOARD))
            }
            R.id.invoice -> {
                swapFragment(getFragment(tag = INVOICE))
            }
            R.id.activity -> {
                swapFragment(getFragment(tag = ACTIVITY))
            }
        }
        this.selectPosition = menuItem.itemId
    }

    private fun swapFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }

    private fun getFragment(tag: String): Fragment {
        val existFragment = supportFragmentManager.findFragmentByTag(tag)
        if (existFragment != null) {
            return existFragment
        }
        val newFragment = when (tag) {
            DASHBOARD -> DashboardFragment()
            INVOICE -> InvoiceFragment()
            ACTIVITY -> ActivityFragment()
            else -> DashboardFragment()
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.container, newFragment, tag)
            .commit()
        return newFragment
    }

    interface MainEventOrder {
        fun onClickButtonView()
        fun onClickProfile()
    }

    interface CallBackProfile {
        fun onClickClose()
        fun onClickPrinter()
        fun onClickChangePassword()
        fun onClickLogout()
    }

    companion object {
        const val DASHBOARD = "dashboard"
        const val INVOICE = "invoice"
        const val ACTIVITY = "activity"
    }
}