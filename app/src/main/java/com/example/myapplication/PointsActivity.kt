package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PointsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_quiz_finished)

        val buttonHome = findViewById<ImageButton>(R.id.returnHome)
        val points = findViewById<TextView>(R.id.pointNumber)

        points.text = String.format("You are %s years old", MediorActivity.wrongAnswers)

        buttonHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}