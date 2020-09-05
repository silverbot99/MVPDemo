package com.example.mvpdemo.screen.main_statistic

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpdemo.R
import com.example.mvpdemo.base.config.Constant.Companion.doubleToStringNoDecimal
import com.example.mvpdemo.base.config.Constant.Companion.formatNumber
import com.example.mvpdemo.screen.main_statistic.presentation.MainStatisticsContract
import com.example.mvpdemo.screen.main_statistic.presentation.MainStatisticsPresenter
import com.example.mvpdemo.screen.main_statistic.presentation.model.MainStatisticViewModel
import com.example.mvpdemo.screen.statistics.presentation.StatisticsContract
import com.example.mvpdemo.screen.statistics.presentation.StatisticsPresenter
import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel
import com.example.mvpdemo.screen.statistics.presentation.renderer.StatisticsAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.synthetic.main.item_layout_statistics.*
import kotlinx.android.synthetic.main.layout_main_statistic.*
import kotlinx.android.synthetic.main.layout_statistics.*
import java.net.InetAddress
import java.net.UnknownHostException


class MainStatisticsFragment: Fragment(),
    MainStatisticsContract.MainStatisticView {
    var listData: MutableList<ItemStatisticsViewModel> = mutableListOf()
    lateinit var progressBar : ProgressBar
    var presenter=
        MainStatisticsPresenter(
            this
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_main_statistic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        initView(view)
    }

    lateinit var tvTitle : TextView
    private fun initView(view: View) {
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
        nsvMain.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        nsvMain.visibility = View.VISIBLE
    }

    override fun showToast(msg: String) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(context,error,Toast.LENGTH_LONG).show()
    }


    override fun showData(model: MainStatisticViewModel) {
        tvTime.text = model.nowTime
        tvTotalCases.text = doubleToStringNoDecimal(model.totalWorldCases.toDouble())

        tvTotalActive.text = doubleToStringNoDecimal(model.totalActiveCases.toDouble())
        tvMildConditionActive.text = model.newCases
        tvCriticalActive.text = doubleToStringNoDecimal(model.critical.toDouble())

        tvTotalClosed.text = doubleToStringNoDecimal(model.totalClosedCased.toDouble())
        tvRecoverClosed.text = model.newRecovered
        tvTest.text = doubleToStringNoDecimal(model.totalTest.toDouble())

        tvTotalDeaths.text = doubleToStringNoDecimal(model.totalDeath.toDouble())
        tvMildCondition.text = model.newDeaths
        tvOnePop.text = model.OnePop
    }
}