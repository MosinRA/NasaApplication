package com.mosin.nasaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mosin.nasaapplication.fragment.PictureOfTheDayFragment
import com.mosin.nasaapplication.fragment.ThemeHolder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeHolder.theme)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                    .commitNow()
        }
    }
}