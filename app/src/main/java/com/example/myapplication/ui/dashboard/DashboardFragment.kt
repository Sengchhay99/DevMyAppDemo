package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashBoardBinding
import com.example.myapplication.helper.MyValueFormatter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*

class DashboardFragment : Fragment(R.layout.fragment_dash_board) {

    private val binding: FragmentDashBoardBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardPieChart(binding.pieToday)
        dashboardPieChart(binding.pieYesterday)
        dashboardBarChart(binding.barChartThisWeek)
        dashboardBarChart(binding.barChartThisMonth)
    }

    private fun dashboardPieChart(pieToday: PieChart){
        val entries = mutableListOf<PieEntry>()
        entries.add(PieEntry(33.11f))
        entries.add(PieEntry(13.09f))
        entries.add(PieEntry(13.02f))

        val dataSet = PieDataSet(entries, "")
        dataSet.setDrawValues(false)

        val colorList = mutableListOf<Int>()
        colorList.add(ContextCompat.getColor(requireContext(), R.color.color_white_green))
        colorList.add(ContextCompat.getColor(requireContext(), R.color.color_white_red))
        colorList.add(ContextCompat.getColor(requireContext(), R.color.color_white_purple))
        dataSet.colors = colorList

        pieToday.apply {
            setUsePercentValues(false)
            description.isEnabled = false
            legend.isEnabled = false

            val pieData = PieData(dataSet)
            data = pieData
        }
    }

    private fun dashboardBarChart(barChart : BarChart){

        val barGroup1 = listOf(
            600, 1100, 900
        )
        val barGroup2 = listOf(
            700, 1200, 1000
        )

        val entriesGroup1 = mutableListOf<BarEntry>()
        val entriesGroup2 = mutableListOf<BarEntry>()

        barGroup1.forEachIndexed { index, data ->
            entriesGroup1.add(BarEntry(index.toFloat(), data.toFloat()))
        }

        barGroup2.forEachIndexed { index, data ->
            entriesGroup2.add(BarEntry(index.toFloat(), data.toFloat()))
        }

        val set1 = BarDataSet(entriesGroup1, "This Week")
        val set2 = BarDataSet(entriesGroup2, "Last Week")

        set1.setColors(ContextCompat.getColor(requireContext(), R.color.color_dark_blue))
        set2.setColors(ContextCompat.getColor(requireContext(), R.color.color_dark_green))

        set1.setDrawValues(false)
        set2.setDrawValues(false)

        val groupSpace = 0.06f
        val barSpace = 0.02f // x2 dataset
        val barWidth = 0.45f // x2 dataset
        // (0.02 + 0.45) * 2 + 0.06 = 1.00 -> interval per "group"

        val data = BarData(set1, set2)
        data.barWidth = barWidth

        val xAxis = barChart.xAxis
        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = 3f
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = MyValueFormatter()

        val leftAxis = barChart.axisLeft
        leftAxis.isEnabled = false

        val rightAxis = barChart.axisRight
        rightAxis.setDrawAxisLine(false)
        rightAxis.granularity = 200f

        barChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        barChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        barChart.description.isEnabled = false
        barChart.data = data
        barChart.groupBars(0f, groupSpace, barSpace)
    }

}