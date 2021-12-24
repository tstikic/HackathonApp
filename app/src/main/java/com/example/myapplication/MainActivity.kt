package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object {
        const val DIFFICULTY_BUNDLE_KEY = "DIFFICULTY_BUNDLE_KEY"
        const val NUMBER_OF_ANSWERS = 3

        @ColorInt
        const val PROGRESS_BAR_COLOR = 0xFFe16e68
    }

    private var currentQuestion = 0

    private var correctAnswerIndex = 0

    var wrongAnswers = 10

    private lateinit var data: List<Question>

    private lateinit var difficulty: Difficulty

    private var timePerQuestion: Long = 10000

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

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        colorStatusBar(R.color.teal)
        setContentView(R.layout.screen_quiz)

        difficulty =
            intent.getSerializableExtra(DIFFICULTY_BUNDLE_KEY) as? Difficulty ?: Difficulty.JUNIOR
        data = when (difficulty) {
            Difficulty.JUNIOR -> juniorData
            Difficulty.MEDIOR -> mediorData
            Difficulty.SENIOR -> seniorData
        }
        timePerQuestion = when (difficulty) {
            Difficulty.JUNIOR -> 10000
            Difficulty.MEDIOR -> 9000
            Difficulty.SENIOR -> 7000
        }

        questionTextView = findViewById(R.id.question)
        buttonFirst = findViewById(R.id.buttonFirst)
        buttonSecond = findViewById(R.id.buttonSecond)
        buttonThird = findViewById(R.id.buttonThird)
        textFirst = findViewById(R.id.answer1)
        textSecond = findViewById(R.id.answer2)
        textThird = findViewById(R.id.answer3)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar.progressTintList = ColorStateList.valueOf(PROGRESS_BAR_COLOR.toInt())

        animator = ValueAnimator.ofInt(0, progressBar.max)
        animator.duration = 10000
        animator.addUpdateListener { animation ->
            progressBar.progress = animation.animatedValue as Int
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (!buttonFirst.isSelected && !buttonSecond.isSelected && !buttonThird.isSelected) {
                    nextQuestion()
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
        startActivity(
            Intent(this@MainActivity, PointsActivity::class.java).apply {
                putExtra(PointsActivity.POINTS_BUNDLE_KEY, wrongAnswers)
            }
        )
        finish()
    }

    private fun onAnswerSelected(index: Int): View.OnClickListener = View.OnClickListener {
        it.isSelected = true
        if (!isCorrectAnswer(index)) {
            onWrongAnswer()
        }
        nextQuestion()
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
        wrongAnswers += 10
    }

    private fun cancelProgressBar() {
        animator.cancel()
    }

    private fun isCorrectAnswer(answeredIndex: Int) = answeredIndex == correctAnswerIndex

    private fun nextQuestion() {
        if (++currentQuestion < data.size) {
            newQuestion()
        } else {
            gameOver()
        }
    }

    private fun newQuestion() {
        disableButtons()

        val question = data[currentQuestion]

        val shuffledAnswers = question.answers.shuffled()
        correctAnswerIndex = shuffledAnswers.indexOf(question.correctAnswer)

        fadeInText(questionTextView, question.binaryNumber.toString(2))

        for (i in 0 until NUMBER_OF_ANSWERS) {
            with(getButton(i)) {
                fadeInText(getTextView(i), shuffledAnswers[i].toString())
                backgroundTintList = if (i == correctAnswerIndex) {
                    context.getColorStateList(R.color.btn_correct_state)
                } else {
                    context.getColorStateList(R.color.btn_wrong_state)
                }
                isSelected = false
            }
        }

        enableButtons()

        animator.start()
    }

    private fun fadeInText(textView: TextView, newText: String) {
        if (currentQuestion == 0) {
            textView.text = newText
        } else {
            textView.setTextAnimation(newText)
        }
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

enum class Difficulty {
    JUNIOR, MEDIOR, SENIOR
}