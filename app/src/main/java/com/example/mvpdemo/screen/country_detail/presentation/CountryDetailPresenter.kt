package com.example.mvpdemo.screen.country_detail.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import com.example.mvpdemo.base.config.Constant
import com.example.mvpdemo.base.config.Constant.Companion.getValueOrDefaultNull
import com.example.mvpdemo.base.network.ApiInterfac
import com.example.mvpdemo.base.network.CustomApiClient
import com.example.mvpdemo.base.network.response.StatisticsResponse
import com.example.mvpdemo.screen.country_detail.CountryDetailActivity
import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class CountryDetailPresenter(val view: CountryDetailContract.CountryDetailView, val activity: Activity): CountryDetailContract.Presenter() {
    val client = CustomApiClient.getClient()
    @SuppressLint("CheckResult")
    override fun getData(queryCountry :String) {
        if (client!=null) {
            val apiService = client.create(ApiInterfac.ApiInterface::class.java)
            val countryObserver:Observable<StatisticsResponse> =
                apiService.getStatistics(queryCountry)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())

            countryObserver.subscribeWith(object :Observer<StatisticsResponse>{
                override fun onComplete() {
                    view.showToast("Finished")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: StatisticsResponse) {
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
    private fun mapData(response: StatisticsResponse): ItemStatisticsViewModel{
        return ItemStatisticsViewModel(
            cases = ItemStatisticsViewModel.Cases(response.response[0].cases.onePop,response.response[0].cases.active, response.response[0].cases.critical,Constant.stringWithPlusToInt(response.response[0].cases.new.getValueOrDefaultNull()),response.response[0].cases.recovered,response.response[0].cases.total),
            continent = response.response[0].continent,
            country = response.response[0].country,
            day = response.response[0].day,
            deaths = ItemStatisticsViewModel.Cases(onePop = response.response[0].deaths.onePop, new = Constant.stringWithPlusToInt(response.response[0].deaths.new.getValueOrDefaultNull()), total = response.response[0].deaths.total),
            population = response.response[0].population,
            tests = ItemStatisticsViewModel.Cases(onePop = response.response[0].tests.onePop, total = response.response[0].tests.total),
            time = response.response[0].time
        )
    }

}