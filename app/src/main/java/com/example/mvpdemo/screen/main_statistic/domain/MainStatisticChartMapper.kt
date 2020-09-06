package com.example.mvpdemo.screen.main_statistic.domain

import com.example.mvpdemo.base.config.Constant.Companion.getValueOrDefaultZero
import com.example.mvpdemo.base.function.mapper.Mapper
import com.example.mvpdemo.base.network.response.HistoryResponse
import com.example.mvpdemo.screen.main_statistic.presentation.model.SparkViewDataViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainStatisticChartMapper:Mapper<HistoryResponse, SparkViewDataViewModel> {
    override fun map(value: HistoryResponse?): SparkViewDataViewModel {
        val sparkModel = SparkViewDataViewModel()
        value?.let {
            if (!value.response.isNullOrEmpty()) {
                val firstDayCase: HistoryResponse.Response? = value.response.first()
                val aprilCase = value.response.filter { it.day == "2020-04-20" }.first()
                val mayCase = value.response.filter { it.day == "2020-05-20" }.first()
                val juneCase = value.response.filter { it.day == "2020-06-20" }.first()
                val julyCase = value.response.filter { it.day == "2020-07-20" }.first()
                val augustCase = value.response.filter { it.day == "2020-08-20" }.first()
                val septemberCase = value.response.filter { it.day == "2020-09-01" }.first()
                val lastDayCase: HistoryResponse.Response? = value.response.last()

                sparkModel.listTotalActiveCases = mutableListOf(
                    firstDayCase?.cases?.active.getValueOrDefaultZero().toFloat(),
                    aprilCase.cases.active.getValueOrDefaultZero().toFloat(),
                    mayCase.cases.active.getValueOrDefaultZero().toFloat(),
                    juneCase.cases.active.getValueOrDefaultZero().toFloat(),
                    julyCase.cases.active.getValueOrDefaultZero().toFloat(),
                    augustCase.cases.active.getValueOrDefaultZero().toFloat(),
                    septemberCase.cases.active.getValueOrDefaultZero().toFloat(),
                    lastDayCase?.cases?.active.getValueOrDefaultZero().toFloat()
                )

                sparkModel.listTotalDeath = mutableListOf(
                    firstDayCase?.deaths?.total.getValueOrDefaultZero().toFloat(),
                    aprilCase.deaths.total.getValueOrDefaultZero().toFloat(),
                    mayCase.deaths.total.getValueOrDefaultZero().toFloat(),
                    juneCase.deaths.total.getValueOrDefaultZero().toFloat(),
                    julyCase.deaths.total.getValueOrDefaultZero().toFloat(),
                    augustCase.deaths.total.getValueOrDefaultZero().toFloat(),
                    septemberCase.deaths.total.getValueOrDefaultZero().toFloat(),
                    lastDayCase?.deaths?.total.getValueOrDefaultZero().toFloat()
                )

                sparkModel.listRecovered = mutableListOf(
                    firstDayCase?.cases?.recovered.getValueOrDefaultZero().toFloat(),
                    aprilCase.cases.recovered.getValueOrDefaultZero().toFloat(),
                    mayCase.cases.recovered.getValueOrDefaultZero().toFloat(),
                    juneCase.cases.recovered.getValueOrDefaultZero().toFloat(),
                    julyCase.cases.recovered.getValueOrDefaultZero().toFloat(),
                    augustCase.cases.recovered.getValueOrDefaultZero().toFloat(),
                    septemberCase.cases.recovered.getValueOrDefaultZero().toFloat(),
                    lastDayCase?.cases?.recovered.getValueOrDefaultZero().toFloat()
                )
            }
        }
        return sparkModel
    }
}