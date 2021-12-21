package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonOne = findViewById<Button>(R.id.button)
        val buttonTwo = findViewById<Button>(R.id.button2)
        val buttonThree = findViewById<Button>(R.id.button3)
        val buttonFour = findViewById<Button>(R.id.button4)
        val title = findViewById<TextView>(R.id.textView)
        val result = findViewById<TextView>(R.id.textViewResult)
        title.setText("Do you know decimal representation of 1011?")
        buttonOne.setText("13")
        buttonFour.setText("11")
        buttonThree.setText("15")
        buttonTwo.setText("10")
        buttonTwo.background.setTint(R.drawable.btn_wrong_state)
        buttonFour.background.setTint(R.drawable.btn_correct_state)

        buttonTwo.setOnClickListener {
            buttonTwo.isSelected = !buttonTwo.isSelected
            buttonTwo.isClickable = false
            buttonThree.isClickable = false
            buttonFour.isClickable = false
            buttonOne.isClickable = false
            result.setText("You're a looser!")
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
            result.setText("You're a genious!")
        }
    }
}