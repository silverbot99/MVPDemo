package com.example.mvpdemo.screen.country_detail.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ItemCountriesDetailViewModel(
    var newCase: String = "",
    var date: String = "",
    var recovered: Int = 0,
    var critical: Int = 0,
    var deaths: String = "",
    var totalCase: Int = 0,
    var population: Int = 0
):ViewModel