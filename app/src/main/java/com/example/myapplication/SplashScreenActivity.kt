package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        colorStatusBar()
        setContentView(R.layout.splash_screen)
        val imgView = findViewById<ImageView>(R.id.logo)

        imgView.visibility = View.VISIBLE
        imgView.animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }
}