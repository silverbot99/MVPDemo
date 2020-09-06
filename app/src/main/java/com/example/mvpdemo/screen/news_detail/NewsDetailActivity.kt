package com.example.mvpdemo.screen.news_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpdemo.R
import kotlinx.android.synthetic.main.layout_news_detail.*

class NewsDetailActivity: AppCompatActivity(R.layout.layout_news_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val news = intent.getIntExtra("news", 0)
        when(news){
            0 ->  webView.loadUrl("file:///android_asset/news1.html")
            1 ->  webView.loadUrl("file:///android_asset/news2.html")
            2 ->  webView.loadUrl("file:///android_asset/news3.html")
        }
    }
}