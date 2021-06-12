package com.mosin.nasaapplication

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.mosin.nasaapplication.databinding.SplashActivityBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var ui: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = SplashActivityBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.imageView.animate().rotationBy(360f)
            .setInterpolator(AccelerateDecelerateInterpolator()).setDuration(3000)
            .setListener(object : Animator.AnimatorListener {

                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })
    }
}