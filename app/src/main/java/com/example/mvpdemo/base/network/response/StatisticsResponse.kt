package com.example.mvpdemo.base.network.response

import com.google.gson.annotations.SerializedName

class StatisticsResponse (
    @SerializedName("response")
    var response :List<ItemStatistic>,
    @SerializedName("results")
    var results :Int,
    @SerializedName("get")
    var get :String
){
    data class Cases(
        @SerializedName("1M_pop")
        var onePop : String? = null,
        @SerializedName("active")
        var active : Int? = null,
        @SerializedName("critical")
        var critical : Int? = null,
        @SerializedName("new")
        var new : String? = null,
        @SerializedName("recovered")
        var recovered : Int? = null,
        @SerializedName("total")
        var total : Int? = null
    )
    data class ItemStatistic(
        @SerializedName("cases")
        var cases : Cases,
        @SerializedName("continent")
        var continent : String,
        @SerializedName("country")
        var country : String,
        @SerializedName("day")
        var day : String,
        @SerializedName("deaths")
        var deaths : Cases,
        @SerializedName("population")
        var population : Int,
        @SerializedName("tests")
        var tests : Cases,
        @SerializedName("time")
        var time : String
        )
}


