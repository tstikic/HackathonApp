package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity.Companion.DIFFICULTY_BUNDLE_KEY

class HomeActivity : AppCompatActivity() {

    private lateinit var buttonJunior: ImageButton
    private lateinit var buttonMedior: ImageButton
    private lateinit var buttonSenior: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        setContentView(R.layout.new_activity)

        buttonJunior = findViewById(R.id.playBtn)
        buttonMedior = findViewById(R.id.playBtnMedior)
        buttonSenior = findViewById(R.id.playBtnSenior)

        buttonJunior.setOnClickListener{
            startMainActivity(Difficulty.JUNIOR)
        }

        buttonMedior.setOnClickListener{
            startMainActivity(Difficulty.MEDIOR)
        }

        buttonSenior.setOnClickListener{
            startMainActivity(Difficulty.SENIOR)
        }
    }

    fun startMainActivity(difficulty: Difficulty) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(DIFFICULTY_BUNDLE_KEY, difficulty)
        }
        startActivity(intent)
        finish()
    }
}