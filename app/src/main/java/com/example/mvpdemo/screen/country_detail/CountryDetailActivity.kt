package com.example.mvpdemo.screen.country_detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpdemo.R
import com.example.mvpdemo.screen.country_detail.presentation.CountryDetailContract
import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.roger.catloadinglibrary.CatLoadingView

class CountryDetailActivity: AppCompatActivity(R.layout.activity_main) , CountryDetailContract.CountryDetailView {
    private val catLoadingView = CatLoadingView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initChart()
    }

    private fun initChart() {
        var increaseValue = listOf<Int>(100,150,360)
        var listEntry = mutableListOf<Entry>()
        listEntry.add(Entry(0f,increaseValue[0].toFloat()))
        listEntry.add(Entry(1f,increaseValue[1].toFloat()))
        listEntry.add(Entry(5f,increaseValue[2].toFloat()))
        val dataSet = LineDataSet(listEntry,"")
//        dataSet.set
    }

    override fun showLoading() {
        catLoadingView.show(this.supportFragmentManager,"")
    }

    override fun hideLoading() {
        catLoadingView.dismiss()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this,error, Toast.LENGTH_LONG).show()
    }

    override fun showData(list: ItemStatisticsViewModel) {

    }

}
