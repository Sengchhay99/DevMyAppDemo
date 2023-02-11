package com.example.myapplication.model

data class PaymentOrderModel(
    val id: Int,
    val name: String,
    val date: String,
    val price: String,
    val status: String,
    val iconImage: Int
) : java.io.Serializable

