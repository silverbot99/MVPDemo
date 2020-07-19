package com.example.mvpdemo.screen.statistics

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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


class StatisticsFragment: Fragment(),
    StatisticsContract.StatisticsView {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: StatisticsAdapter
    var listData: MutableList<ItemStatisticsViewModel> = mutableListOf()
    var presenter=
        StatisticsPresenter(
            this
        )
    //var catLoading: CatLoadingView = CatLoadingView()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        initView(view)
    }

//    private fun initProgressBar() {
//        val doubleBounce: Sprite = DoubleBounce()
//        progressBar.indeterminateDrawable = doubleBounce
//    }

    lateinit var tvTitle : TextView
    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.rvStatics)
        tvTitle= view.findViewById<TextView>(R.id.tvTitle)
        progressBar.isIndeterminate = true;
    }


    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
        if (context!=null){
            if (isNetworkAvailable(requireContext()) || isInternetAvailable()){
                presenter.getData()
            }
            else{
                showToast("Please turn on your network!")
            }
        }
    }

    override fun showLoading() {
//        activity?.let {
//            MainActivity.catLoadingView.show(it.supportFragmentManager,"")
//        }
        rvStatics.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
//        MainActivity.catLoadingView.dismiss()
        rvStatics.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun showToast(msg: String) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(context,error,Toast.LENGTH_LONG).show()
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
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}