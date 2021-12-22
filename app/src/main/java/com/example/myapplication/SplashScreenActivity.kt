package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_layout)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}