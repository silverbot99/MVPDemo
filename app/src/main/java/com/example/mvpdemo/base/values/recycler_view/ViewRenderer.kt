package com.example.mvpdemo.base.values.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

abstract class ViewRenderer<M:ViewModel, VH: RecyclerView.ViewHolder> {
    abstract fun bindView(model: M, holder: VH)
    abstract fun createViewHolder(parent:ViewGroup): VH
}