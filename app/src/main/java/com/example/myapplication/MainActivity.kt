package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.animation.Animator

import android.animation.AnimatorListenerAdapter

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.res.ColorStateList
import android.graphics.Color

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler

    private val data = listOf(
        Question(
            0b10,
            1, 3, 4
        ),
        Question(
            0b110,
            3, 9, 5
        ),
        Question(
            0b1001,
            5, 3, 11
        ),
        Question(
            0b1011,
            17, 15, 13
        ),
        Question(
            0b10101,
            11, 19, 13
        ),
        Question(
            0b11001,
            23, 21, 22
        ),
        Question(
            0b100101,
            43, 53, 47
        ),
        Question(
            0b100100,
            34, 32, 30
        )
    )

    var currentQuestion = 0

    var correctAnswerIndex = 0

    private lateinit var questionTextView: TextView

    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonFour: Button

    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_quiz)

        //questionTextView = findViewById(R.id.questionTextView)
        buttonOne = findViewById(R.id.button)
        buttonTwo = findViewById(R.id.button2)
        buttonThree = findViewById(R.id.button3)
        buttonFour = findViewById(R.id.button4)
        result = findViewById(R.id.textViewResult)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)


        fillQuestionFields()
        for (i in 0 until 4) {
            getButton(i).setOnClickListener(onAnswerSelected(i))
        }



        progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));


        val animator = ValueAnimator.ofInt(0, progressBar.max)
        animator.duration = 10000
        animator.addUpdateListener { animation ->
            progressBar.progress = (animation.animatedValue as Int)!!
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (!buttonOne.isSelected && !buttonTwo.isSelected && !buttonThree.isSelected) {

                    startActivity(Intent(this@MainActivity, SecondActivity::class.java))
                }
            }
        })
        animator.start()

        handler = Handler()




    }

    private fun onAnswerSelected(index: Int): View.OnClickListener = View.OnClickListener {
        if (isCorrectAnswer(index)) {
            nextQuestion()
        } else {
            onWrongAnswer()
        }
    }

    private fun onWrongAnswer() {
        result.text = "Wrong answer"
        for (i in 1 until 4) {
            getButton(i).isClickable = false
        }
    }

    private fun isCorrectAnswer(answeredIndex: Int) = answeredIndex == correctAnswerIndex

    private fun nextQuestion() {
        if (++currentQuestion < data.size) {
            fillQuestionFields()
        }
    }

    private fun fillQuestionFields() {
        val question = data[currentQuestion]

        val shuffledAnswers = question.answers.shuffled()
        correctAnswerIndex = shuffledAnswers.indexOf(question.correctAnswer)

        questionTextView.text = question.binaryNumber.toString(2)

        for (i in 0 until 4) {
            getButton(i).text = shuffledAnswers[i].toString()
        }
    }

    private fun getButton(index: Int) = when (index) {
        0 -> buttonOne
        1 -> buttonTwo
        2 -> buttonThree
        3 -> buttonFour
        else -> throw IllegalArgumentException()
    }
}

data class Question(
    val binaryNumber: Int,
    val answer2: Int,
    val answer3: Int,
    val answer4: Int,
) {
    val correctAnswer = Integer.parseInt(binaryNumber.toString(2), 2)

    val answers
        get() = listOf(correctAnswer, answer2, answer3, answer4)
}