package com.example.mvpdemo.screen.country.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.mvpdemo.MainActivity
import com.example.mvpdemo.base.network.ApiInterfac
import com.example.mvpdemo.base.network.CustomApiClient
import com.example.mvpdemo.base.network.response.CountriesResponse
import com.example.mvpdemo.screen.country.presentation.CountriesContract.CountriesView
import com.example.mvpdemo.screen.country.presentation.renderer.CountriesMapper
import com.example.mvpdemo.screen.country_detail.CountryDetailActivity
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
        view.showLoading()
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
                    view.hideLoading()
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: CountriesResponse) {
                    if (t.response!=null || t.response.size!=0){
                        val isSearch = !queryCountry.isNullOrBlank()
                        view.showData(CountriesMapper(isSearch).map(t))
                    }
                }

                override fun onError(e: Throwable) {
                    view.showError(e.toString())
                    view.hideLoading()
                }

            })
        }
        else{
            view.showToast("Null client")
        }
    }
}