package com.example.mvpdemo.screen.statistics

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpdemo.R
import com.example.mvpdemo.screen.statistics.presentation.StatisticsContract
import com.example.mvpdemo.screen.statistics.presentation.StatisticsPresenter
import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel
import com.example.mvpdemo.screen.statistics.presentation.renderer.StatisticsAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.synthetic.main.layout_statistics.*
import java.net.InetAddress
import java.net.UnknownHostException


class StatisticsActivity: AppCompatActivity(R.layout.layout_statistics),
    StatisticsContract.StatisticsView {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: StatisticsAdapter
    var listData: MutableList<ItemStatisticsViewModel> = mutableListOf()
    var presenter=
        StatisticsPresenter(
            this
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
        initView()
    }

    private fun initView() {
        recyclerView = this.findViewById(R.id.rvStatics)
        progressBar.isIndeterminate = true;
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo
            .isConnected
    }
    private fun isInternetAvailable(): Boolean {
        try {
            val address: InetAddress = InetAddress.getByName("www.google.com")
            return !address.equals("")
        } catch (e: UnknownHostException) {
            Log.e("Error","$e")
        }
        return false
    }

    private fun checkPermission() {
        if (isNetworkAvailable(this) || isInternetAvailable()){
            presenter.getData()
        }
        else{
            showToast("Please turn on your network!")
        }
    }

    override fun showLoading() {
        rvStatics.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rvStatics.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    }

    override fun showData(list: MutableList<ViewModel>) {
        listData.clear()
        list.forEach {
            if (it is ItemStatisticsViewModel) listData.add(it)
        }

        adapter =
            StatisticsAdapter(
                listData.sortedByDescending { item -> item.cases.new }.toMutableList()
            )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}