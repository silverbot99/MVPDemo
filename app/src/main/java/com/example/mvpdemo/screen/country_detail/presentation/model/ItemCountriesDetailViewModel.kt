package com.example.mvpdemo.screen.country_detail.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ItemCountriesDetailViewModel(
    var newCase: String,
    var date: String,
    var recovered: Int,
    var critical: Int,
    var deaths: String
):ViewModel