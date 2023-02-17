package com.example.myapplication.helper

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class MyValueFormatter : ValueFormatter() {

    private val labelAxis = listOf("INT", "OUT", "TAX")

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return labelAxis.getOrNull(value.toInt()) ?: value.toString()
    }
}