package com.example.mvpdemo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mvpdemo.screen.country.CountriesFragment
import com.example.mvpdemo.screen.main_statistic.MainStatisticsFragment
import com.example.mvpdemo.screen.news.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navigation = this.findViewById<BottomNavigationView>(R.id.bottomBar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigation)
        replaceFragment(MainStatisticsFragment())
    }
    private val mOnNavigation= object :BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.first_fragment -> {
                    replaceFragment(MainStatisticsFragment())
                    return true
                }
                R.id.second_fragment -> {
                    replaceFragment(CountriesFragment())
                    return true
                }
                R.id.third_fragment -> {
                    replaceFragment(NewsFragment())
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
}