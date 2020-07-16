package com.example.mvpdemo.screen.country.presentation.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpdemo.R

class CountriesAdapter(var list: MutableList<String>): RecyclerView.Adapter<ItemCountryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_countries,parent,false)
        return ItemCountryViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemCountryViewHolder, position: Int) {
        holder.mCountry.text = list[position]
    }

}
