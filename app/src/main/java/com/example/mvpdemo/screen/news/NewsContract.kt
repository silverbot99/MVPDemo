package com.example.mvpdemo.screen.news

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

interface NewsContract {
    interface View{
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