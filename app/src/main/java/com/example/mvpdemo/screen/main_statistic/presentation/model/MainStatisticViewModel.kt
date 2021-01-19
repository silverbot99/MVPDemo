package com.example.mvpdemo.screen.main_statistic.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class MainStatisticViewModel(
    var nowTime: String,
    var totalWorldCases: Int,

    var totalActiveCases: Int,
    var newCases: String,
    var critical: Int,

    var totalDeath:Int,
    var newDeaths:String,
    var OnePop:String,

    var totalClosedCased: Int,
    var newRecovered: String,
    var totalTest:Int
):ViewModel