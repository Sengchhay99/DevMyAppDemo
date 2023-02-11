package com.example.myapplication.model.invoice

data class InvoiceModel(
    var id: Int ,
    var itemName: String,
    var qty: String,
    var price: String,
    var total: String
): java.io.Serializable
