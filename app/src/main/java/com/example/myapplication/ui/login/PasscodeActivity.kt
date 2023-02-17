package com.example.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.NumberKeyListener
import android.viewbinding.library.activity.viewBinding
import com.davidmiguel.numberkeyboard.NumberKeyboardListener
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.config.Constant
import com.example.myapplication.databinding.ActivityPasscodeBinding
import com.example.myapplication.helper.Session
import com.example.myapplication.ui.mainActivity.MainActivity
import `in`.aabhasjindal.otptextview.OTPListener

class PasscodeActivity : BaseActivity(){

    private val binding : ActivityPasscodeBinding by viewBinding()

    private var setPasscode = ""

    private var isSetPasscode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.numPad.setListener(numPadListener)
        binding.otpView.otpListener = otpListener

        Session.initContext(this)

    }

    private val numPadListener = object : NumberKeyboardListener {
        override fun onLeftAuxButtonClicked() {}

        override fun onNumberClicked(number: Int) {
           binding.otpView.apply {
               setOTP(this.otp.toString() + number.toString())
           }
        }

        override fun onRightAuxButtonClicked() {
            binding.otpView.apply {
                if (this.otp.isNullOrEmpty()) return
                otp?.dropLast(1)?.let {
                    setOTP(it)
                }
            }
        }
    }

    private val otpListener = object : OTPListener {
        override fun onInteractionListener() {}

        override fun onOTPComplete(otp: String) {

            if (isSetPasscode) {
                if (otp != setPasscode){
                    binding.otpView.setOTP("")
                    binding.otpView.showError()
                    showErrorMessage(" Invalid Passcode")
                    return
                }
                Session.putString(Constant.User.OTP, otp)
                binding.otpView.setOTP("")
                start<MainActivity> {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
            } else {
                isSetPasscode = true
                setPasscode = otp
                binding.otpView.setOTP("")
                binding.titlePasscode.text = getString(R.string.confirm_your_passcode)
            }

            }
        }
}