package com.example.mvpdemo.screen.main_statistic.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class MainStatisticViewModel(
    var nowTime: String,
    var totalWorldCases: Int,

    var totalActiveCases: Int,
    var newCases: String,
    var critical: Int,
    var listActiveCase :MutableList<Int> = mutableListOf(),

    var totalDeath:Int,
    var newDeaths:String,
    var OnePop:String,
    var listDeath :MutableList<Int> = mutableListOf(),

    var totalClosedCased: Int,
    var newRecovered: String,
    var totalTest:Int,
    var listRecovered :MutableList<Int> = mutableListOf(),

    var listRequirement: MutableList<ItemRequirement> = mutableListOf()
):ViewModel{
    data class ItemRequirement(
        var drawable : Int,
        var name: String
    ):ViewModel
}