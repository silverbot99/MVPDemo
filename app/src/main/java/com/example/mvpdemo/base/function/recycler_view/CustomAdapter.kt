package com.example.mvpdemo.base.function.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

open class CustomAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mItem = mutableListOf<ViewModel>()
    private lateinit var mRenderer :  ViewRenderer<ViewModel, RecyclerView.ViewHolder>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return mRenderer.createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mItem.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mRenderer.bindView(mItem[position],holder)

    }

//    override fun getItemViewType(position: Int): Int {
//        val item :ViewModel = getItem(position)
//        return
//    }

    protected fun getItem(position: Int): ViewModel {
        return mItem.get(position)
    }
    protected fun registerRenderer(renderer: ViewRenderer<ViewModel, RecyclerView.ViewHolder>){
        mRenderer = renderer
    }

    protected fun setItems(items: MutableList<ViewModel>){
        mItem.clear()
        mItem.addAll(items)
    }

//    public interface ItemModel: ViewModel{
//        fun getType(): Int
//    }


}