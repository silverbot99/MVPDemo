package com.example.mvpdemo.screen.main_statistic.presentation

import com.example.mvpdemo.screen.main_statistic.presentation.model.MainStatisticViewModel
import com.example.mvpdemo.screen.main_statistic.presentation.model.SparkViewDataViewModel

interface MainStatisticsContract {
    interface MainStatisticView{
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showError(error: String)
        fun showData(model: MainStatisticViewModel)
        fun showDataSparkView(model: SparkViewDataViewModel)
    }
    abstract class Presenter{
        abstract fun getData()
        abstract fun getDataStatisticTime()
    }
}