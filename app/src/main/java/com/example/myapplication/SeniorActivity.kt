package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.IntRange
import androidx.appcompat.app.AppCompatActivity

class SeniorActivity : AppCompatActivity() {

    companion object {
        const val NUMBER_OF_ANSWERS = 3
    }

    private val data = listOf(
        Question(
            0b10,
            1, 3
        ),
        Question(
            0b110,
            3, 9
        ),
        Question(
            0b1001,
            5, 11
        ),
        Question(
            0b1011,
            17, 15
        ),
        Question(
            0b10101,
            11, 19
        ),
        Question(
            0b11001,
            23, 21
        ),
        Question(
            0b100101,
            43, 53
        ),
        Question(
            0b100100,
            34, 32
        )
    )

    private var currentQuestion = 0

    private var correctAnswerIndex = 0

    private lateinit var questionTextView: TextView
    private lateinit var buttonFirst: ImageButton
    private lateinit var buttonSecond: ImageButton
    private lateinit var buttonThird: ImageButton
    private lateinit var textFirst: TextView
    private lateinit var textSecond: TextView
    private lateinit var textThird: TextView

    private lateinit var animator: ValueAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_quiz)

        questionTextView = findViewById(R.id.question)
        buttonFirst = findViewById(R.id.buttonFirst)
        buttonSecond = findViewById(R.id.buttonSecond)
        buttonThird = findViewById(R.id.buttonThird)
        textFirst = findViewById(R.id.answer1)
        textSecond = findViewById(R.id.answer2)
        textThird = findViewById(R.id.answer3)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar.progressTintList = ColorStateList.valueOf(Color.RED)

        animator = ValueAnimator.ofInt(0, progressBar.max)
        animator.duration = 7000
        animator.addUpdateListener { animation ->
            progressBar.progress = animation.animatedValue as Int
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (!buttonFirst.isSelected && !buttonSecond.isSelected && !buttonThird.isSelected) {
                    gameOver()
                }
            }
        })

        for (i in 0 until NUMBER_OF_ANSWERS) {
            getButton(i).setOnClickListener(onAnswerSelected(i))
        }
        newQuestion()
    }

    private fun gameOver() {
        cancelProgressBar()
        disableButtons()
        startActivity(Intent(this@SeniorActivity, PointsActivity::class.java))
    }

    private fun onAnswerSelected(index: Int): View.OnClickListener = View.OnClickListener {
        if (isCorrectAnswer(index)) {
            nextQuestion()
        } else {
            onWrongAnswer()
        }
    }

    private fun disableButtons() {
        for (i in 1 until NUMBER_OF_ANSWERS) {
            getButton(i).isClickable = false
        }
    }

    private fun enableButtons() {
        for (i in 1 until NUMBER_OF_ANSWERS) {
            getButton(i).isClickable = true
        }
    }

    private fun onWrongAnswer() {
        gameOver()
    }

    private fun cancelProgressBar() {
        animator.cancel()
    }

    private fun isCorrectAnswer(answeredIndex: Int) = answeredIndex == correctAnswerIndex

    private fun nextQuestion() {
        if (++currentQuestion < data.size) {
            newQuestion()
        }
    }

    private fun newQuestion() {
        disableButtons()

        val question = data[currentQuestion]

        val shuffledAnswers = question.answers.shuffled()
        correctAnswerIndex = shuffledAnswers.indexOf(question.correctAnswer)

        questionTextView.text = question.binaryNumber.toString(2)

        for (i in 0 until NUMBER_OF_ANSWERS) {
            with(getButton(i)) {
                getTextView(i).text = shuffledAnswers[i].toString()
                backgroundTintList = if (i == correctAnswerIndex) {
                    context.getColorStateList(R.color.btn_correct_state)
                } else {
                    context.getColorStateList(R.color.btn_wrong_state)
                }
            }
        }

        enableButtons()

        animator.start()
    }

    private fun getButton(@IntRange(from = 0, to = 2) index: Int) = when (index) {
        0 -> buttonFirst
        1 -> buttonSecond
        2 -> buttonThird
        else -> throw IllegalArgumentException("i = $index")
    }

    private fun getTextView(@IntRange(from = 0, to = 2) index: Int) = when (index) {
        0 -> textFirst
        1 -> textSecond
        2 -> textThird
        else -> throw IllegalArgumentException("i = $index")
    }
}