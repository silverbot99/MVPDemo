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
        initMutilBarChar(barChart)
    }
    private var listInitData = mutableListOf<Project>(
        Project("Sunrise City",226f,146f,226f),
        Project("Adora Garden",50f,250f,300f),
        Project("Royal City",225f,115f,160f),
        Project("Luxury Palace",100f,215f,350f)).reversed()

    private val colorArray = arrayListOf(Color.LTGRAY, Color.GRAY, Color.CYAN)

    private fun barSelling(): ArrayList<BarEntry> {
        val barEntries = arrayListOf<BarEntry>()
        listInitData.forEachIndexed { index, project ->
            barEntries.add(BarEntry((index+1).toFloat(),project.selling))
        }
        return barEntries
    }

    private fun barBooking(): ArrayList<BarEntry> {
        val barEntries = arrayListOf<BarEntry>()
        listInitData.forEachIndexed { index, project ->
            barEntries.add(BarEntry((index+1).toFloat(),project.booking))
        }
        return barEntries
    }

    private fun barSold(): ArrayList<BarEntry> {
        val barEntries = arrayListOf<BarEntry>()
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
        val barSetSelling = BarDataSet(barSelling(),"")
        barSetSelling.setColors(Color.parseColor("#6bbcd7"))
        barSetSelling.valueTextColor = Color.parseColor("#6bbcd7")
        barSetSelling.setDrawValues(true)

        val barSetBooking = BarDataSet(barBooking(),"")
        barSetBooking.setColors(Color.parseColor("#d8d8d8"))
        barSetBooking.valueTextColor = Color.parseColor("#d8d8d8")
        barSetBooking.setDrawValues(true)

        val barSetSold = BarDataSet(barSold(),"")
        barSetSold.setColors(Color.parseColor("#9a59dc"))
        barSetSold.valueTextColor = Color.parseColor("#9a59dc")
        barSetSold.setDrawValues(true)

        val data = BarData(barSetSold,barSetBooking,barSetSelling)
        data.isHighlightEnabled = false
        chart.data = data

        val projects = arrayListOf<String>()
        listInitData.forEach {
            projects.add(it.name)
        }
        val xAxis = chart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(projects)
        xAxis.setCenterAxisLabels(true)
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 0.5f
        xAxis.isGranularityEnabled = false
        chart.isDragEnabled = false // De khong keo len keo xuong
        chart.setScaleEnabled(false); // De khong zoom duoc
        chart.axisRight.setDrawGridLines(false);
        chart.xAxis.setDrawGridLines(false); // bo
        chart.xAxis.axisLineColor = Color.parseColor("#f3f3f8")
        chart.axisLeft.setDrawAxisLine(false)
        chart.axisRight.setDrawAxisLine(false)
        chart.axisLeft.gridColor = Color.parseColor("#f3f3f8")
        chart.axisLeft.setDrawLabels(false);
        chart.axisRight.setDrawLabels(true);
        chart.description.isEnabled = false;
        chart.legend.isEnabled = false;
        chart.axisLeft.axisMaximum = 400f;
        chart.axisLeft.axisMinimum = 0f;



//        chart.setVisibleXRangeMaximum(3f)
        val barSpace = 0.00f
        val groupSpace = 0.4f
        data.barWidth = 0.15f
        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = dataBarLabels().size - 1.1f
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