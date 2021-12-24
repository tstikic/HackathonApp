package com.example.myapplication

data class Question(
    val binaryNumber: Int,
    val answer2: Int,
    val answer3: Int,
) {
    val correctAnswer = Integer.parseInt(binaryNumber.toString(2), 2)

    val answers
        get() = listOf(correctAnswer, answer2, answer3)
}