package com.example.mvpdemo.screen.country.presentation.renderer

import android.view.View
import android.widget.TextView
import com.example.mvpdemo.R
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder

class ItemCountryAlphabetViewHolder(var view: View) : ViewHolder(view) {
    var alpha = view.findViewById<TextView>(R.id.tvAlphabet)
}
