package com.example.mvpdemo.screen.statistics.presentation

import com.example.mvpdemo.base.network.ApiInterfac
import com.example.mvpdemo.base.network.CustomApiClient
import com.example.mvpdemo.base.network.response.CountriesResponse
import com.example.mvpdemo.base.network.response.StatisticsResponse
import com.example.mvpdemo.screen.statistics.domain.StatisticsMapper
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatisticsPresenter(val view: StatisticsContract.StatisticsView): StatisticsContract.Presenter() {
    private val client = CustomApiClient.getClient()
    val apiService = client?.create(ApiInterfac.ApiInterface::class.java)
    var compositeDisposable = CompositeDisposable()
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
                            view.showData(StatisticsMapper().map(t))
                        }

                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.toString())
                    }

                })
            }
        }

    }

    override fun getDataByCountry(country: String) {
        view.showLoading()
        apiService?.let {
//            compositeDisposable.add(it.getStatisticsByCountry(country))

        }
    }

}