package com.mosin.nasaapplication.bottomNav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mosin.nasaapplication.R
import com.mosin.nasaapplication.databinding.ActivityBottomNavBinding
import com.mosin.nasaapplication.viewPager.EarthFragment
import com.mosin.nasaapplication.viewPager.MarsFragment
import com.mosin.nasaapplication.viewPager.WeatherFragment

class ActivityBottomNav: AppCompatActivity() {

    private var ui: ActivityBottomNavBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(ui?.root)
        ui?.bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, MarsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, WeatherFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
        ui?.bottomNavigationView?.selectedItemId = R.id.bottom_view_earth
        ui?.bottomNavigationView?.getOrCreateBadge(R.id.bottom_view_earth)
        val badge = ui?.bottomNavigationView?.getBadge(R.id.bottom_view_earth)
        badge?.maxCharacterCount = 2
        badge?.number = 999

        ui?.bottomNavigationView?.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    //Item tapped
                }
                R.id.bottom_view_mars -> {
                    //Item tapped
                }
                R.id.bottom_view_weather -> {
                    //Item tapped
                }
            }
        }
    }
}
