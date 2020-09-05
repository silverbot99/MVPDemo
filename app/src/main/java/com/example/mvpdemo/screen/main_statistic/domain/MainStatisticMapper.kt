package com.example.mvpdemo.screen.main_statistic.domain

import com.example.mvpdemo.base.config.Constant.Companion.getValueOrDefault
import com.example.mvpdemo.base.config.Constant.Companion.getValueOrDefaultZero
import com.example.mvpdemo.base.function.mapper.Mapper
import com.example.mvpdemo.base.network.response.StatisticsResponse
import com.example.mvpdemo.screen.main_statistic.presentation.model.MainStatisticViewModel

class MainStatisticMapper:Mapper<StatisticsResponse, MainStatisticViewModel>{
    override fun map(value: StatisticsResponse?): MainStatisticViewModel {
        val allCases  =  value?.response?.find {it.country=="All" }
        return  MainStatisticViewModel(
            totalWorldCases = allCases?.cases?.total.getValueOrDefaultZero(),
            nowTime = allCases?.time.getValueOrDefault("N/A"),

            totalActiveCases = allCases?.cases?.active.getValueOrDefaultZero(),
            newCases = allCases?.cases?.new.getValueOrDefault("N/A"),
            critical = allCases?.cases?.critical.getValueOrDefaultZero(),

            totalDeath = allCases?.deaths?.total.getValueOrDefaultZero(),
            newDeaths = allCases?.deaths?.new.getValueOrDefault("N/A"),
            OnePop = allCases?.deaths?.onePop.getValueOrDefault("0"),

            totalClosedCased = allCases?.cases?.recovered.getValueOrDefaultZero(),
            newRecovered = "N/A",
            totalTest = allCases?.tests?.total.getValueOrDefaultZero()
        )
    }
}