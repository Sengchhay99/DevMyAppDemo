package com.example.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private val binding: ActivityLoginBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.actionLogin = clickActionButton
    }

    private val clickActionButton = object : ClickActionLoginCallback{
        override fun clickButtonLoginListener() {
            val userName = binding.userNameText.text.toString()
            val password = binding.passwordText.text.toString()

            if (userName  == "" && password == "") {
                showErrorMessage("username and password invalid")
                return
            }

            if (userName == "admin" && password == "123456"){
              start<PasscodeActivity>()
            } else{
                showErrorMessage("wrong username and password")
            }

        }
    }

    interface ClickActionLoginCallback{
        fun clickButtonLoginListener()
    }
}