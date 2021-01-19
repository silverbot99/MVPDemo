package com.example.mvpdemo.screen.news.presentation.renderer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mvpdemo.R
import com.example.mvpdemo.base.function.pass_data.OnNotifyData
import com.example.mvpdemo.screen.news.presentation.model.ItemNewsViewModel
import com.github.vivchar.rendererrecyclerviewadapter.ViewRenderer

class NewsViewRenderer(context: Context?, modelClass: Class<ItemNewsViewModel>, val onNotifyData: OnNotifyData):
    ViewRenderer<ItemNewsViewModel, ItemNewsViewHolder>(modelClass) {
    override fun bindView(model: ItemNewsViewModel, holder: ItemNewsViewHolder) {
        if (context!=null){
            Glide.with(context).load(model.newsAvatar).into(holder.imageView)
        }
        holder.textView.text = model.title
        holder.view.setOnClickListener {
            onNotifyData.onNotify(model)
        } // click event
    }

    override fun createViewHolder(parent: ViewGroup): ItemNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_news,parent,false)
        return ItemNewsViewHolder(
            view
        )
    }
}