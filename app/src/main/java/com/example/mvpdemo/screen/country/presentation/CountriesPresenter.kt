package com.example.mvpdemo.screen.country.presentation

import android.annotation.SuppressLint
import android.util.Log
import com.example.mvpdemo.base.network.ApiInterfac
import com.example.mvpdemo.base.network.CustomApiClient
import com.example.mvpdemo.base.network.response.CountriesResponse
import com.example.mvpdemo.screen.country.presentation.CountriesContract.CountriesView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class CountriesPresenter(val view: CountriesView): CountriesContract.Presenter() {
    val client = CustomApiClient.getClient()
    @SuppressLint("CheckResult")
    override fun getData(queryCountry :String?) {
        if (client!=null) {
            val apiService = client.create(ApiInterfac.ApiInterface::class.java)
            val countryObserver:Observable<CountriesResponse> = if (queryCountry=="null"|| queryCountry=="") {apiService.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())}
            else{
                apiService.getCountries(queryCountry)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
            }

            countryObserver.subscribeWith(object :Observer<CountriesResponse>{
                override fun onComplete() {
                    view.showToast("Finished")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: CountriesResponse) {
                    if (t.response!=null || t.response.size!=0){
                        view.showData(t.response)
                    }
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

    override fun goToCountryDetailInfoFragment():List<String> {
        return listOf("q","f")
    }
}