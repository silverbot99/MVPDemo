package com.example.mvpdemo.screen.main_statistic.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class SparkViewDataViewModel(
    var listTotalActiveCases: MutableList<Float> = mutableListOf(),
    var listTotalDeath: MutableList<Float> = mutableListOf(),
    var listRecovered: MutableList<Float> = mutableListOf()
):ViewModel