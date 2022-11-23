package com.eea_tech_interview.section.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import com.eea_tech_interview.R
import com.eea_tech_interview.section.main.MainActivity

class SplashScreen : AppCompatActivity() {
    private val splashTimeOut = 2500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startAnimations()
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashTimeOut.toLong())
    }
    private fun startAnimations() {
        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim.reset()
        val l = findViewById<View>(R.id.lin_lay) as LinearLayout
        l.clearAnimation()
        l.startAnimation(anim)
        anim = AnimationUtils.loadAnimation(this, R.anim.bottom)
        anim.reset()
        val iv = findViewById<View>(R.id.logo) as ImageView
        iv.clearAnimation()
        iv.startAnimation(anim)
    }
}