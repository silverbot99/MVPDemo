package com.example.mvpdemo.screen.statistics.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import java.util.*

class ItemStatisticsViewModel (
    var cases : Cases,
    var continent : String,
    var country : String,
    var day : String,
    var deaths : Cases,
    var population : Int,
    var tests : Cases,
    var time : String
): ViewModel{
    data class Cases (
        var onePop : String? = null,
        var active : Int? = null,
        var critical : Int? = null,
        var new : String? = null,
        var recovered : Int? = null,
        var total : Int? = null
    )

}