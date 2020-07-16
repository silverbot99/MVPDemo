package com.example.mvpdemo.base.network.response

import com.google.gson.annotations.SerializedName

class CountriesResponse (
    @SerializedName("response")
    var response :List<String>,
    @SerializedName("results")
    var results :Int,
    @SerializedName("get")
    var get :String
)


