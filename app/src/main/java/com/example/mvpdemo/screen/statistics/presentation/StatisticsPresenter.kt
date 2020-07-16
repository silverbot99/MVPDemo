package com.example.mvpdemo.screen.statistics.presentation

import com.example.mvpdemo.base.network.ApiInterfac
import com.example.mvpdemo.base.network.CustomApiClient
import com.example.mvpdemo.base.network.response.StatisticsResponse
import com.example.mvpdemo.screen.statistics.domain.StatisticsMapper
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatisticsPresenter(val view: StatisticsContract.StatisticsView): StatisticsContract.Presenter() {
    private val client = CustomApiClient.getClient()
    val apiService = client?.create(ApiInterfac.ApiInterface::class.java)
    var compositeDisposable = CompositeDisposable()
    override fun getData() {
        view.showLoading()
        apiService?.let { it ->
            val call = it.getStatistics()
            call.enqueue(object : Callback<StatisticsResponse>{
                override fun onResponse(
                    call: Call<StatisticsResponse>,
                    response: Response<StatisticsResponse>
                ) {
                    if (response.isSuccessful){
                        val statistics: StatisticsResponse? = response.body()
                        statistics?.let {
                            if (!statistics.response.isNullOrEmpty()){
                                view.showData(StatisticsMapper().map(statistics))
                                view.hideLoading()
                            }
                            else{
                                view.showError(response.message())
                            }
                        }
                    }
                    else {
                        view.hideLoading()
                        view.showError(response.message())
                    }
                }
                override fun onFailure(call: Call<StatisticsResponse>, t: Throwable) {
                    view.hideLoading()
                    view.showError(t.toString())
                }

            })
        }

    }

    override fun getDataByCountry(country: String) {
        view.showLoading()
        apiService?.let {
//            compositeDisposable.add(it.getStatisticsByCountry(country))

        }
    }
}