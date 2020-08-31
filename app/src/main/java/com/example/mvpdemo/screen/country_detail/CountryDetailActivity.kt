package com.example.mvpdemo.screen.country_detail

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpdemo.R
import com.example.mvpdemo.screen.country_detail.presentation.CountryDetailContract
import com.example.mvpdemo.screen.country_detail.presentation.CountryDetailPresenter
import com.example.mvpdemo.screen.country_detail.presentation.model.ItemCountriesDetailViewModel
import com.robinhood.spark.SparkAdapter
import com.robinhood.spark.SparkView
import kotlinx.android.synthetic.main.item_layout_toolbar.*
import kotlinx.android.synthetic.main.layout_country_detail.*
import kotlinx.android.synthetic.main.layout_country_detail.progressBar
import java.util.*


class CountryDetailActivity: AppCompatActivity(R.layout.layout_country_detail)
    ,CountryDetailContract.CountryDetailView {
    private var mYear =0
    private  var mMonth:Int = 0
    private  var mDay:Int = 0
    var presenter=
        CountryDetailPresenter(
            this
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initChart()
        initView()
        getInfoCountry()
    }

    private fun initView() {
        progressBar.isIndeterminate = true
        tvTitle.text = "Thông tin chi tiết"
        ivCalendar.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    showToast("getData: $dayOfMonth/${monthOfYear+1}/$year")
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
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
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showToast(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this,error, Toast.LENGTH_LONG).show()
    }

    override fun showData(list: List<ItemCountriesDetailViewModel>) {

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
