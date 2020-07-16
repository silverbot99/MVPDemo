package com.example.mvpdemo.screen.country

import android.content.Context
import android.os.Bundle
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
import com.example.mvpdemo.screen.country.presentation.CountriesContract
import com.example.mvpdemo.screen.country.presentation.CountriesPresenter
import com.example.mvpdemo.screen.country.presentation.renderer.CountriesAdapter
import com.roger.catloadinglibrary.CatLoadingView
import kotlinx.android.synthetic.main.layout_countries.*


class CountriesFragment: Fragment(), CountriesContract.CountriesView {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CountriesAdapter
    var listData: MutableList<String> = mutableListOf()
    var presenter=
        CountriesPresenter(
            this
        )
    //var catLoading: CatLoadingView = CatLoadingView()

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
        presenter.getData("")
        initSearchView()
    }

    private fun initSearchView() {
        svCountry.setOnQueryTextListener(onQueryTextChange)
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
        activity?.let {
            MainActivity.catLoadingView.show(it.supportFragmentManager,"")
        }
    }

    override fun hideLoading() {
        MainActivity.catLoadingView.dismiss()
    }

    override fun showToast(msg: String) {
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(context,error, Toast.LENGTH_LONG).show()
    }

//    override fun getData(data: String) {
//    }

    override fun showData(list: List<String>) {
        listData.clear()
        listData.addAll(list)
        adapter =
            CountriesAdapter(
                listData
            )
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}