package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity)

        val buttonJunior = findViewById<ImageButton>(R.id.playBtn)

        buttonJunior.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}