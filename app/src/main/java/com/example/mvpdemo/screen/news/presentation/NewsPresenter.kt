package com.example.mvpdemo.screen.news.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.mvpdemo.MainActivity
import com.example.mvpdemo.R
import com.example.mvpdemo.base.network.ApiInterfac
import com.example.mvpdemo.base.network.CustomApiClient
import com.example.mvpdemo.base.network.response.CountriesResponse
import com.example.mvpdemo.screen.country.presentation.CountriesContract.CountriesView
import com.example.mvpdemo.screen.country.presentation.renderer.CountriesMapper
import com.example.mvpdemo.screen.country_detail.CountryDetailActivity
import com.example.mvpdemo.screen.news.NewsContract
import com.example.mvpdemo.screen.news.presentation.model.ItemNewsViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class NewsPresenter(val view: NewsContract.View): NewsContract.Presenter() {
    val client = CustomApiClient.getClient()
    @SuppressLint("CheckResult")
    override fun getData(queryCountry :String?) {
        view.showData(mutableListOf(
            ItemNewsViewModel(id = 0,htmlName = "news1.html", title = "Khi quên mang khẩu trang, nên làm gì để phòng dịch?", newsAvatar = R.drawable.img_news1),
            ItemNewsViewModel(id = 1, htmlName = "news2.html",title="Các bước cần làm khi đi khám bệnh để phòng COVID-19", newsAvatar = R.drawable.img_news2),
            ItemNewsViewModel(id = 2, htmlName = "news3.html", title="4 lầm tưởng về phòng bệnh viêm phổi cấp Corona", newsAvatar = R.drawable.img_news3)
        ))
    }
}