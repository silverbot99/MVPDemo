package com.example.mvpdemo.screen.statistics.domain

import com.example.mvpdemo.base.function.mapper.Mapper
import com.example.mvpdemo.base.network.response.StatisticsResponse
import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class StatisticsMapper: Mapper<StatisticsResponse,MutableList<ViewModel>> {
    override fun map(value: StatisticsResponse): MutableList<ViewModel> {
        var list : MutableList<ViewModel> = mutableListOf()
        value.response.forEach {
            list.add(
                ItemStatisticsViewModel(
                    cases = ItemStatisticsViewModel.Cases(it.cases.onePop.getValueOrDefaultNull(),it.cases.active.getValueOrDefaultZero(), it.cases.critical.getValueOrDefaultZero(),it.cases.new.getValueOrDefaultNull(),it.cases.recovered.getValueOrDefaultZero(),it.cases.total.getValueOrDefaultZero()),
                    continent = it.continent.getValueOrDefaultNull(),
                    country = it.country.getValueOrDefaultNull(),
                    day = it.day.getValueOrDefaultNull(),
                    deaths = ItemStatisticsViewModel.Cases(onePop = it.deaths.onePop.getValueOrDefaultNull(), new = it.deaths.new.getValueOrDefaultNull(), total = it.deaths.total.getValueOrDefaultZero()),
                    population = it.population.getValueOrDefaultZero(),
                    tests = ItemStatisticsViewModel.Cases(onePop = it.tests.onePop.getValueOrDefaultNull(), total = it.tests.total.getValueOrDefaultZero()),
                    time = it.time.getValueOrDefaultNull()
                ))
        }
        return list
    }
    fun String?.getValueOrDefault(defaultValue: String): String {
        return if (this.isNullOrEmpty()) defaultValue
        else this
    }
    fun String?.getValueOrDefaultNull():String{
        return if (this.isNullOrEmpty()) return "null"
        else this
    }
    fun Int?.getValueOrDefaultZero():Int{
        return this ?: return 0
    }
}