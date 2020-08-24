package com.example.mvpdemo.base.network.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("errors")
    var errors: List<Any>,
    @SerializedName("get")
    var get: String,
    @SerializedName("parameters")
    var parameters: Parameters,
    @SerializedName("response")
    var response: List<Response>,
    @SerializedName("results")
    var results: Int
){
    data class Parameters(
        @SerializedName("country")
        var country: String,
        @SerializedName("day")
        var day: String
    )

    data class Response(
        @SerializedName("cases")
        var cases: Cases,
        @SerializedName("continent")
        var continent: String,
        @SerializedName("country")
        var country: String,
        @SerializedName("day")
        var day: String,
        @SerializedName("deaths")
        var deaths: Deaths,
        @SerializedName("population")
        var population: Int,
        @SerializedName("tests")
        var tests: Tests,
        @SerializedName("time")
        var time: String
    )

    data class Cases(
        @SerializedName("active")
        var active: Int,
        @SerializedName("critical")
        var critical: Int,
        @SerializedName("1M_pop")
        var mPop: String,
        @SerializedName("new")
        var new: String,
        @SerializedName("recovered")
        var recovered: Int,
        @SerializedName("total")
        var total: Int
    )

    data class Deaths(
        @SerializedName("1M_pop")
        var mPop: String,
        @SerializedName("new")
        var new: String,
        @SerializedName("total")
        var total: Int
    )

    data class Tests(
        @SerializedName("1M_pop")
        var mPop: String,
        @SerializedName("total")
        var total: Int
    )
}

