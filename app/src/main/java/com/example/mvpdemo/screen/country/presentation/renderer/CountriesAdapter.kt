package com.example.mvpdemo.screen.country.presentation.renderer

import android.view.ViewGroup
import com.example.mvpdemo.base.function.recycler_view.ViewRenderer
import com.example.mvpdemo.screen.country.presentation.model.ItemCountryViewModel
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter

class CountriesAdapter(var list: MutableList<String>): RendererRecyclerViewAdapter(){


}





/*class CountriesAdapter(var list: MutableList<String>): RecyclerView.Adapter<ItemCountryViewHolder>(){
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

}*/