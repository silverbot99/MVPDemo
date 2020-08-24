package com.example.mvpdemo.screen.country_detail.presentation

import com.example.mvpdemo.screen.country_detail.presentation.model.ItemCountriesDetailViewModel
interface CountryDetailContract {
    interface CountryDetailView{
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showError(error: String)
        fun showData(list: List<ItemCountriesDetailViewModel>)
    }
    abstract class Presenter{
        abstract fun getData(date :String, country: String)
    }
}