package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var buttonJunior: ImageButton
    private lateinit var buttonMedior: ImageButton
    private lateinit var buttonSenior: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity)

        buttonJunior = findViewById(R.id.playBtn)
        buttonMedior = findViewById(R.id.playBtnMedior)
        buttonSenior = findViewById(R.id.playBtnSenior)

        buttonJunior.setOnClickListener{
            val intent = Intent(this, JuniorActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonMedior.setOnClickListener{
            val intent = Intent(this, MediorActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonSenior.setOnClickListener{
            val intent = Intent(this, SeniorActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}