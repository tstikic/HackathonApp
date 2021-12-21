package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonOne = findViewById<Button>(R.id.button)
        val buttonTwo = findViewById<Button>(R.id.button2)
        val buttonThree = findViewById<Button>(R.id.button3)
        val buttonFour = findViewById<Button>(R.id.button4)
        val result = findViewById<TextView>(R.id.textViewResult)

        buttonTwo.setOnClickListener {
            buttonTwo.isSelected = !buttonTwo.isSelected
            buttonTwo.isClickable = false
            buttonThree.isClickable = false
            buttonFour.isClickable = false
            buttonOne.isClickable = false
            result.setText("You're a genious!")
            handler = Handler()
            handler.postDelayed({
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }

        buttonOne.setOnClickListener {
            buttonOne.isSelected = !buttonOne.isSelected
            buttonTwo.isClickable = false
            buttonThree.isClickable = false
            buttonFour.isClickable = false
            buttonOne.isClickable = false
            result.setText("You're a looser!")
        }

        buttonThree.setOnClickListener {
            buttonThree.isSelected = !buttonThree.isSelected
            buttonTwo.isClickable = false
            buttonThree.isClickable = false
            buttonFour.isClickable = false
            buttonOne.isClickable = false
            result.setText("You're a looser!")
        }

        buttonFour.setOnClickListener {
            buttonFour.isSelected = !buttonFour.isSelected
            buttonTwo.isClickable = false
            buttonThree.isClickable = false
            buttonFour.isClickable = false
            buttonOne.isClickable = false
            result.setText("You're a looser!")
        }
    }
}