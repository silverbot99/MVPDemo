package com.example.mvpdemo.screen.country.presentation.renderer

import com.example.mvpdemo.base.function.mapper.Mapper
import com.example.mvpdemo.base.network.response.CountriesResponse
import com.example.mvpdemo.screen.country.presentation.model.ItemCountryAlphabetViewModel
import com.example.mvpdemo.screen.country.presentation.model.ItemCountryViewModel
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class CountriesMapper(private val isSearch:Boolean):Mapper<CountriesResponse,MutableList<ViewModel>> {
    override fun map(value: CountriesResponse?): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val list = mutableListOf<ViewModel>()
        val listAlphabet = mutableListOf<String>("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        value?.response?.forEachIndexed { index, s ->
            list.add(ItemCountryViewModel(index,s))
        }
        return if(isSearch){
            list
        } else{
            listAlphabet.forEach {key->
                listReturn.add(ItemCountryAlphabetViewModel(key))
                listReturn.addAll(list.filter { it is ItemCountryViewModel && it.name.startsWith(key)})
            }
            listReturn
        }

// add word in first country start with that word

    }
}