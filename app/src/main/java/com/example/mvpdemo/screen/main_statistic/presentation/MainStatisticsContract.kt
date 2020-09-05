package com.example.mvpdemo.screen.main_statistic.presentation

import com.example.mvpdemo.screen.main_statistic.presentation.model.MainStatisticViewModel

interface MainStatisticsContract {
    interface MainStatisticView{
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showError(error: String)
        fun showData(model: MainStatisticViewModel)
    }
    abstract class Presenter{
        abstract fun getData()
        abstract fun getDataRequirement()
    }
}