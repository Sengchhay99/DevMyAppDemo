package com.example.myapplication.app

import android.app.Application
import com.kongzue.dialogx.DialogX

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DialogX.init(this)
    }
}