package com.mosin.nasaapplication.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.mosin.nasaapplication.R
import com.mosin.nasaapplication.databinding.ActivityPagerBinding


private const val EARTH = 0
private const val MARS = 1
private const val WEATHER = 2

class ActivityPager : AppCompatActivity() {

    private var ui: ActivityPagerBinding?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(ui?.root)

        ui?.viewPager?.adapter = ViewPagerAdapter(supportFragmentManager)
        ui?.tabLayout?.setupWithViewPager(ui?.viewPager)
        setHighlightedTab(EARTH)
        ui?.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                setHighlightedTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })
    }

    private fun setHighlightedTab(position: Int) {
        val layoutInflater = LayoutInflater.from(this@ActivityPager)

        ui?.tabLayout?.getTabAt(EARTH)?.customView = null
        ui?.tabLayout?.getTabAt(MARS)?.customView = null
        ui?.tabLayout?.getTabAt(WEATHER)?.customView = null

        when (position) {
            EARTH -> {
                setEarthTabHighlighted(layoutInflater)
            }
            MARS -> {
                setMarsTabHighlighted(layoutInflater)
            }
            WEATHER -> {
                setWeatherTabHighlighted(layoutInflater)
            }
            else -> {
                setEarthTabHighlighted(layoutInflater)
            }
        }
    }

    private fun setEarthTabHighlighted(layoutInflater: LayoutInflater) {
        val earth =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_earth, null)
        earth.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@ActivityPager,
                    R.color.colorAccent
                )
            )
        ui?.tabLayout?.getTabAt(EARTH)?.customView = earth
        ui?.tabLayout?.getTabAt(MARS)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_mars, null)
        ui?.tabLayout?.getTabAt(WEATHER)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_weather, null)
    }

    private fun setMarsTabHighlighted(layoutInflater: LayoutInflater) {
        val mars =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_mars, null)
        mars.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@ActivityPager,
                    R.color.colorAccent
                )
            )
        ui?.tabLayout?.getTabAt(EARTH)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_earth, null)
        ui?.tabLayout?.getTabAt(MARS)?.customView = mars
        ui?.tabLayout?.getTabAt(WEATHER)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_weather, null)
    }

    private fun setWeatherTabHighlighted(layoutInflater: LayoutInflater) {
        val weather =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_weather, null)
        weather.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@ActivityPager,
                    R.color.colorAccent
                )
            )
        ui?.tabLayout?.getTabAt(EARTH)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_earth, null)
        ui?.tabLayout?.getTabAt(MARS)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_mars, null)
        ui?.tabLayout?.getTabAt(WEATHER)?.customView = weather
    }
}