package com.example.mvpdemo.screen.country.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

interface CountriesContract {
    interface CountriesView{
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showError(error: String)
        fun showData(list: MutableList<ViewModel>)
    }
    abstract class Presenter{
        abstract fun getData(queryCountry :String?)
    }
}