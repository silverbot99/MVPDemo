package com.example.mvpdemo.screen.country.presentation.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvpdemo.R
import com.example.mvpdemo.base.function.pass_data.OnNotifyData
import com.example.mvpdemo.screen.country.presentation.model.ItemCountryViewModel
import com.github.vivchar.rendererrecyclerviewadapter.ViewRenderer

class CountriesViewRenderer(modelClass: Class<ItemCountryViewModel>, val onNotifyData: OnNotifyData):
    ViewRenderer<ItemCountryViewModel, ItemCountryViewHolder>(modelClass) {
    override fun bindView(model: ItemCountryViewModel, holder: ItemCountryViewHolder) {
        holder.mCountry.text = model.name
        holder.view.setOnClickListener {
            onNotifyData.onNotify(model)
        } // click event
    }

    override fun createViewHolder(parent: ViewGroup): ItemCountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_countries,parent,false)
        return ItemCountryViewHolder(
            view
        )
    }
}