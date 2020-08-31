package com.example.mvpdemo.screen.country_detail.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import com.example.mvpdemo.base.config.Constant
import com.example.mvpdemo.base.config.Constant.Companion.getValueOrDefaultNull
import com.example.mvpdemo.base.config.Constant.Companion.getValueOrDefaultZero
import com.example.mvpdemo.base.network.ApiInterfac
import com.example.mvpdemo.base.network.CustomApiClient
import com.example.mvpdemo.base.network.response.HistoryResponse
import com.example.mvpdemo.base.network.response.StatisticsResponse
import com.example.mvpdemo.screen.country_detail.CountryDetailActivity
import com.example.mvpdemo.screen.country_detail.presentation.model.ItemCountriesDetailViewModel
import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class CountryDetailPresenter(val view: CountryDetailContract.CountryDetailView): CountryDetailContract.Presenter() {
    val client = CustomApiClient.getClient()
    @SuppressLint("CheckResult")
    override fun getData(date :String, country: String) {
        if (client!=null) {
            val apiService = client.create(ApiInterfac.ApiInterface::class.java)
            val historyObserver:Observable<HistoryResponse> =
                apiService.getHistory(day = date, country = country)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())

            historyObserver.subscribeWith(object :Observer<HistoryResponse>{
                override fun onComplete() {
                    view.showToast("Finished")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: HistoryResponse) {
                    view.showData(mapData(t))
                }

                override fun onError(e: Throwable) {
                    view.showError(e.toString())
                }

            })
        }
        else{
            view.showToast("Null client")
        }
    }
    private fun mapData(response: HistoryResponse): List<ItemCountriesDetailViewModel> {
        val listReturn = mutableListOf<ItemCountriesDetailViewModel>()
        if (!response.response.isNullOrEmpty()){
            response.response.forEach {
                listReturn.add(ItemCountriesDetailViewModel(
                    newCase = it.cases.new.getValueOrDefaultNull(),
                    date = it.day.getValueOrDefaultNull(),
                    recovered = it.cases.recovered.getValueOrDefaultZero(),
                    critical = it.cases.critical.getValueOrDefaultZero(),
                    deaths = it.deaths.new.getValueOrDefaultNull()
                ))
            }
        }
        return listReturn
    }

}