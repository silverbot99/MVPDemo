package com.example.mvpdemo.screen.country_detail.presentation

import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

interface CountryDetailContract {
    interface CountryDetailView{
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showError(error: String)
        fun showData(list: ItemStatisticsViewModel)
    }
    abstract class Presenter{
        abstract fun getData(queryCountry :String)
        abstract fun goToCountryDetailInfoFragment()
    }
}