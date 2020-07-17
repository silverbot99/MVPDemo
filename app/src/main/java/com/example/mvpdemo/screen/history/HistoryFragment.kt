package com.example.mvpdemo.screen.history

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpdemo.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class HistoryFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val barChart = view.findViewById<HorizontalBarChart>(R.id.hBarChart)
        //initBarChart(barChart)
        initMutilBarChar(barChart)
    }
    private var listInitData = mutableListOf<Project>(
        Project("Sunrise City",226f,146f,226f),
        Project("Adora Garden",50f,250f,300f),
        Project("Royal City",225f,115f,160f))

    private val colorArray = arrayListOf(Color.LTGRAY, Color.GRAY, Color.CYAN)

    private fun barSelling(): ArrayList<BarEntry> {
        val barEntries = arrayListOf<BarEntry>()
//        barEntries.add(BarEntry(1f,2000f))
//        barEntries.add(BarEntry(2f,791f))
//        barEntries.add(BarEntry(3f,630f))
//        barEntries.add(BarEntry(4f,782f))
//        barEntries.add(BarEntry(5f,2714.54f))
//        barEntries.add(BarEntry(6f,500f))
//        barEntries.add(BarEntry(7f,2173.36f))
        listInitData.forEachIndexed { index, project ->
            barEntries.add(BarEntry((index+1).toFloat(),project.selling))
        }

        return barEntries
    }

    private fun barBooking(): ArrayList<BarEntry> {
        val barEntries = arrayListOf<BarEntry>()
//        barEntries.add(BarEntry(1f,900f))
//        barEntries.add(BarEntry(2f,691f))
//        barEntries.add(BarEntry(3f,1030f))
//        barEntries.add(BarEntry(4f,382f))
//        barEntries.add(BarEntry(5f,2714f))
//        barEntries.add(BarEntry(6f,5000f))
//        barEntries.add(BarEntry(7f,1173f))
        listInitData.forEachIndexed { index, project ->
            barEntries.add(BarEntry((index+1).toFloat(),project.booking))
        }
        return barEntries
    }

    private fun barSold(): ArrayList<BarEntry> {
        val barEntries = arrayListOf<BarEntry>()
//        barEntries.add(BarEntry(1f,800f))
//        barEntries.add(BarEntry(2f,453f))
//        barEntries.add(BarEntry(3f,382f))
//        barEntries.add(BarEntry(4f,2000f))
//        barEntries.add(BarEntry(5f,900f))
//        barEntries.add(BarEntry(6f,500f))
//        barEntries.add(BarEntry(7f,691f))
        listInitData.forEachIndexed { index, project ->
            barEntries.add(BarEntry((index+1).toFloat(),project.sold))
        }
        return barEntries
    }

    private fun dataBarLabels():MutableList<String>{
        val dataLabels = mutableListOf<String>()
        listInitData.forEach {
            dataLabels.add(it.name)
        }
        return dataLabels
    }
    private fun initMutilBarChar(chart: HorizontalBarChart){
        val barDataSet1 = BarDataSet(barSelling(),"")
        barDataSet1.setColors(Color.parseColor("#59B3D0"))
        val barDataSet2 = BarDataSet(barBooking(),"")
        barDataSet2.setColors(Color.parseColor("#924CD3"))
        val barDataSet3 = BarDataSet(barSold(),"")
        barDataSet3.setColors(Color.parseColor("#D3D3D3"))

        val data = BarData(barDataSet1,barDataSet2,barDataSet3)
        chart.data = data

        val days = arrayListOf<String>()
        listInitData.forEach {
            days.add(it.name)
        }

        val xAxis = chart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(days)
        xAxis.setCenterAxisLabels(true)
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 0.5f
        xAxis.isGranularityEnabled = true
        chart.isDragEnabled = true
        chart.setVisibleXRangeMaximum(3f)
        val barSpace = 0.00f
        val groupSpace = 0.32f
        data.barWidth = 0.08f
        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = 0 + chart.barData.getGroupWidth(groupSpace, barSpace)*listInitData.size
        chart.groupBars(0f,groupSpace, barSpace)
        chart.invalidate()
    }

    private fun initBarChart(chart: BarChart){
        val dataBarEntry = arrayListOf<BarEntry>()
        var barWidth = 9f
        var spaceForBar = 10f

        listInitData.forEachIndexed { index, project ->
            dataBarEntry.add(BarEntry(index*spaceForBar,project.booking))
            dataBarEntry.add(BarEntry(index*spaceForBar+1f,project.selling))
            dataBarEntry.add(BarEntry(index*spaceForBar+2f,project.sold))

        }
        var set = BarDataSet(dataBarEntry,"Bar Data")
        chart.setDrawGridBackground(false)
        var barData = BarData(set)
        barData.barWidth = barWidth
        chart.data = barData
        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(true)
        chart.setMaxVisibleValueCount(50)
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)
        chart.moveViewTo(listInitData.count().toFloat(),0f, YAxis.AxisDependency.LEFT)
        chart.invalidate()


        chart.xAxis.valueFormatter = IndexAxisValueFormatter(dataBarLabels())
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.granularity = 1.5f
        chart.xAxis.isGranularityEnabled = true

    }
    class Project(
        var name: String,
        var selling:Float,
        var booking:Float,
        var sold:Float
    ):ViewModel
}