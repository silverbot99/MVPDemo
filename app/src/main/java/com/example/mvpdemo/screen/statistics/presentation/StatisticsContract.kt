package com.example.mvpdemo.screen.statistics.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

interface StatisticsContract {
    interface StatisticsView{
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showError(error: String)
        fun showData(list: MutableList<ViewModel>)
    }
    abstract class Presenter{
        abstract fun getData()
        abstract fun getDataByCountry(country:String)
    }
}