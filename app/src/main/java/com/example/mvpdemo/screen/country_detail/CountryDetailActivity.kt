package com.example.mvpdemo.screen.country_detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpdemo.R
import com.example.mvpdemo.screen.country_detail.presentation.CountryDetailContract
import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel
import com.robinhood.spark.SparkAdapter
import com.robinhood.spark.SparkView
import com.roger.catloadinglibrary.CatLoadingView
import kotlinx.android.synthetic.main.layout_country_detail.*


class CountryDetailActivity: AppCompatActivity(R.layout.layout_country_detail)
    ,CountryDetailContract.CountryDetailView {
    private val catLoadingView = CatLoadingView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initChart()
        getInfoCountry()
    }

    private fun getInfoCountry() {
        val intent = intent
        val loadsCountry = intent.getStringExtra("country")
        if (loadsCountry!=null){
            tvCountryName.text = loadsCountry
        }
    }

    private fun initChart() {
        val sparkView = findViewById<SparkView>(R.id.sparkview)
        val data :FloatArray = floatArrayOf(3f,34f,987f,26283f,85784f,298370f,509446f,104457f)
        sparkView.adapter = MyAdapter(data)
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

    class MyAdapter(private val yData: FloatArray) : SparkAdapter() {
        override fun getCount(): Int {
            return yData.size
        }

        override fun getItem(index: Int): Any {
            return yData[index]
        }

        override fun getY(index: Int): Float {
            return yData[index]
        }

    }

}
