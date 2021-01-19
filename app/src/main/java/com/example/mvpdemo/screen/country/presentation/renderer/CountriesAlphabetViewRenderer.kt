package com.example.mvpdemo.screen.country.presentation.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvpdemo.R
import com.example.mvpdemo.screen.country.presentation.model.ItemCountryAlphabetViewModel
import com.github.vivchar.rendererrecyclerviewadapter.ViewRenderer

class CountriesAlphabetViewRenderer(modelClass: Class<ItemCountryAlphabetViewModel>):
    ViewRenderer<ItemCountryAlphabetViewModel, ItemCountryAlphabetViewHolder>(modelClass) {
    override fun bindView(model: ItemCountryAlphabetViewModel, holder: ItemCountryAlphabetViewHolder) {
        holder.alpha.text = model.value
    }

    override fun createViewHolder(parent: ViewGroup): ItemCountryAlphabetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_alphabet_country,parent,false)
        return ItemCountryAlphabetViewHolder(
            view
        )
    }
}