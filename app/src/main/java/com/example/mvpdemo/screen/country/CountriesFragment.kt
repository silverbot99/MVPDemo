package com.example.mvpdemo.screen.country

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpdemo.MainActivity
import com.example.mvpdemo.R
import com.example.mvpdemo.base.function.pass_data.OnNotifyData
import com.example.mvpdemo.screen.country.presentation.CountriesContract
import com.example.mvpdemo.screen.country.presentation.CountriesPresenter
import com.example.mvpdemo.screen.country.presentation.model.ItemCountryAlphabetViewModel
import com.example.mvpdemo.screen.country.presentation.model.ItemCountryViewModel
import com.example.mvpdemo.screen.country.presentation.renderer.CountriesAlphabetViewRenderer
import com.example.mvpdemo.screen.country.presentation.renderer.CountriesViewRenderer
import com.example.mvpdemo.screen.country_detail.CountryDetailActivity
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.synthetic.main.item_layout_toolbar.*
import kotlinx.android.synthetic.main.layout_countries.*
import kotlinx.android.synthetic.main.layout_countries.progressBar


class CountriesFragment: Fragment(), CountriesContract.CountriesView {
    lateinit var recyclerView: RecyclerView
    val adapter: RendererRecyclerViewAdapter = RendererRecyclerViewAdapter()
    var listData: MutableList<ViewModel> = mutableListOf()
    var presenter= CountriesPresenter(
                this
            )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvCountry)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.registerRenderer(CountriesViewRenderer(ItemCountryViewModel::class.java, onNotifyData))
        adapter.registerRenderer(CountriesAlphabetViewRenderer(ItemCountryAlphabetViewModel::class.java))
        recyclerView.adapter = adapter
        presenter.getData("")
        initView()
    }

    val onNotifyData = object :OnNotifyData{
        override fun onNotify(model: ViewModel) {
            if (model is ItemCountryViewModel){
                goToCountryDetailInfoFragment(model.name)
            }
        }
    }

    private fun initView() {
        svCountry.setOnQueryTextListener(onQueryTextChange)
        progressBar.isIndeterminate = true
    }
    private val onQueryTextChange: SearchView.OnQueryTextListener= object :SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            if (query!=null){
                presenter.getData(query)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText!=null){
                presenter.getData(newText)
            }
            return true
        }
    }

    override fun showLoading() {
        rvCountry.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rvCountry.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
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

    fun goToCountryDetailInfoFragment(country:String){
        val intent = Intent (activity, CountryDetailActivity::class.java)
        intent.putExtra("country",country)
        activity?.startActivity(intent)
    }


}