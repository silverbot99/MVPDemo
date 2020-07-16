package com.example.mvpdemo.base.network

import com.example.mvpdemo.base.network.response.CountriesResponse
import com.example.mvpdemo.base.network.response.StatisticsResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


class ApiInterfac {
    interface ApiInterface {

        @GET("statistics")
        fun getStatistics() : Call<StatisticsResponse>

        @GET("countries")
        fun getCountries(@Query("search")searchText:String?=null) : Observable<CountriesResponse>
    }
}