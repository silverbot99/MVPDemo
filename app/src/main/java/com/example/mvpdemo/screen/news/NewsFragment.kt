package com.example.mvpdemo.screen.news

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpdemo.R
import com.example.mvpdemo.base.function.pass_data.OnNotifyData
import com.example.mvpdemo.screen.news.presentation.NewsPresenter
import com.example.mvpdemo.screen.news.presentation.model.ItemNewsViewModel
import com.example.mvpdemo.screen.news.presentation.renderer.NewsViewRenderer
import com.example.mvpdemo.screen.news_detail.NewsDetailActivity
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.synthetic.main.layout_news_list.*

class NewsFragment : Fragment(), NewsContract.View {
    lateinit var recyclerView: RecyclerView
    val adapter: RendererRecyclerViewAdapter = RendererRecyclerViewAdapter()
    var listData: MutableList<ViewModel> = mutableListOf()
    var presenter= NewsPresenter(
        this
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvNews)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.registerRenderer(NewsViewRenderer(context,ItemNewsViewModel::class.java, onNotifyData))
        recyclerView.adapter = adapter
        presenter.getData("")
        initView()
    }

    private fun initView() {
        tvNameNews.text = "Khi quên mang khẩu trang, nên làm gì để phòng dịch?"
        Glide.with(this).load(R.drawable.img_news1).into(ivFirstNews)
        tvReadMore.setOnClickListener {
            goToNewsDetail(0)
        }
    }

    val onNotifyData = object : OnNotifyData {
        override fun onNotify(model: ViewModel) {
            if (model is ItemNewsViewModel){
                goToNewsDetail(model.id)
            }
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showToast(msg: String) {
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(context,error, Toast.LENGTH_LONG).show()
    }

    override fun showData(list: MutableList<ViewModel>) {
        listData.clear()
        listData.addAll(list)
        adapter.setItems(listData)
        adapter.notifyDataSetChanged()
    }

    fun goToNewsDetail(htmlId: Int){
        val intent = Intent (activity, NewsDetailActivity::class.java)
        intent.putExtra("news",htmlId)
        activity?.startActivity(intent)
    }


}