package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PointsActivity : AppCompatActivity() {

    companion object {
        const val POINTS_BUNDLE_KEY = "POINTS_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        colorStatusBar(R.color.teal)
        setContentView(R.layout.screen_quiz_finished)

        val buttonHome = findViewById<ImageButton>(R.id.returnHome)
        val points = findViewById<TextView>(R.id.pointNumber)

        points.text = String.format("You are %s years old", intent.getIntExtra(POINTS_BUNDLE_KEY, 10))

        buttonHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}