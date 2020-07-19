package com.example.mvpdemo.screen.country.presentation.renderer

import android.view.View
import android.widget.TextView
import com.example.mvpdemo.R
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder


class ItemCountryViewHolder(var view: View) : ViewHolder(view) {
    var mCountry = view.findViewById<TextView>(R.id.tvCountry)
}
