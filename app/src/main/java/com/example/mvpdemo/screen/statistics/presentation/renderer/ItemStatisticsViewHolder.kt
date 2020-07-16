package com.example.mvpdemo.screen.statistics.presentation.renderer

import android.view.View
import android.widget.TextView
import com.example.mvpdemo.R
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder


class ItemStatisticsViewHolder(view: View) : ViewHolder(view) {
    var mCountry = view.findViewById<TextView>(R.id.tvCountry)
    var mNewCases = view.findViewById<TextView>(R.id.tvNewCase)
    var mDeaths = view.findViewById<TextView>(R.id.tvDeaths)
    var mTotal = view.findViewById<TextView>(R.id.tvTotal)
}
