package com.example.mvpdemo.screen.news.presentation.renderer

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.mvpdemo.R
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder


class ItemNewsViewHolder(var view: View) : ViewHolder(view) {
    var imageView = view.findViewById<ImageView>(R.id.imageView)
    var textView = view.findViewById<TextView>(R.id.textView)
}
