package com.example.mvpdemo.screen.main_statistic.presentation

import com.example.mvpdemo.base.network.ApiInterfac
import com.example.mvpdemo.base.network.CustomApiClient
import com.example.mvpdemo.base.network.response.HistoryResponse
import com.example.mvpdemo.base.network.response.StatisticsResponse
import com.example.mvpdemo.screen.main_statistic.domain.MainStatisticChartMapper
import com.example.mvpdemo.screen.main_statistic.domain.MainStatisticMapper
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainStatisticsPresenter(val view: MainStatisticsContract.MainStatisticView): MainStatisticsContract.Presenter() {
    private val client = CustomApiClient.getClient()
    val apiService = client?.create(ApiInterfac.ApiInterface::class.java)
    override fun getData() {
        view.showLoading()
        if(apiService!=null) {
            apiService.let { it ->
                val call: Observable<StatisticsResponse> =
                    apiService.getStatistics()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())


                call.subscribeWith(object : Observer<StatisticsResponse> {
                    override fun onComplete() {
                        view.showToast("Finished")
                        view.hideLoading()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: StatisticsResponse) {
                        if (!t.response.isNullOrEmpty()) {
                            view.showData(MainStatisticMapper().map(t))
                        }

                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.toString())
                    }

                })
            }
        }

    }

    override fun getDataStatisticTime() {
        view.showLoading()
        if(apiService!=null) {
            apiService.let { it ->
                val call: Observable<HistoryResponse> =
                    apiService.getHistory(country = "all")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())

                call.subscribeWith(object : Observer<HistoryResponse> {
                    override fun onComplete() {
                        view.hideLoading()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: HistoryResponse) {
                        if (!t.response.isNullOrEmpty()) {
                            view.showDataSparkView(MainStatisticChartMapper().map(t))
                        }

                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.toString())
                    }

                })
            }
        }

    }


}