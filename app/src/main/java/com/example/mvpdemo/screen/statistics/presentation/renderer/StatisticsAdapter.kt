package com.example.mvpdemo.screen.statistics.presentation.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpdemo.R
import com.example.mvpdemo.screen.statistics.presentation.model.ItemStatisticsViewModel

class StatisticsAdapter(private val list: MutableList<ItemStatisticsViewModel>) : RecyclerView.Adapter<ItemStatisticsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemStatisticsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_statistics,parent,false)
        return ItemStatisticsViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemStatisticsViewHolder, position: Int) {
        holder.mCountry.text = list[position].country
        holder.mNewCases.text = list[position].cases.new
        holder.mDeaths.text = list[position].deaths.total.toString()
        holder.mTotal.text = list[position].cases.total.toString()

    }



}
