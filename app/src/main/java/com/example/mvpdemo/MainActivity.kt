package com.example.mvpdemo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mvpdemo.screen.country.CountriesFragment
import com.example.mvpdemo.screen.history.ChartFragment
import com.example.mvpdemo.screen.history.HistoryFragment
import com.example.mvpdemo.screen.statistics.StatisticsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.roger.catloadinglibrary.CatLoadingView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navigation = this.findViewById<BottomNavigationView>(R.id.bottomBar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigation)
        replaceFragment(StatisticsFragment())
    }
    private val mOnNavigation= object :BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.first_fragment -> {
//                    toolbar?.let { it.title = getString(R.string.text_statistics) }
                    replaceFragment(StatisticsFragment())
                    return true
                }
                R.id.second_fragment -> {
//                    toolbar?.let { it.title = getString(R.string.text_list_countries)}
                    replaceFragment(CountriesFragment())
                    return true
                }

            }
            return false
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.flFragment, fragment)
            .addToBackStack(null)
            .commit()
    }
    companion object{
        val catLoadingView = CatLoadingView()
    }


}