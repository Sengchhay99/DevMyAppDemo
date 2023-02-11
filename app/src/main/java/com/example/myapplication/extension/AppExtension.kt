package com.example.myapplication.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateFileName(): String {
    return SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(this)
}