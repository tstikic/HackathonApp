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

val juniorData = listOf(
    Question(
        0b10,
        1, 3
    ),
    Question(
        0b100,
        8, 6
    ),
    Question(
        0b101,
        7, 11
    ),
    Question(
        0b1001,
        13, 11
    ),
    Question(
        0b1011,
        13, 15
    ),
    Question(
        0b1111,
        7, 9
    )
)

val mediorData = listOf(
    Question(
        0b1000,
        4, 16
    ),
    Question(
        0b1010,
        14, 12
    ),
    Question(
        0b1100,
        10, 11
    ),
    Question(
        0b10000,
        32, 24
    ),
    Question(
        0b11000,
        22, 26
    ),
    Question(
        0b100001,
        17, 19
    )
)

val seniorData = mediorData

//val seniorData = listOf(
//    Question(
//        0b10000,
//        8, 12
//    ),
//    Question(
//        0b10100,
//        18, 22
//    ),
//    Question(
//        0b100000,
//        30, 64
//    ),
//    Question(
//        0b100010,
//        32, 24
//    ),
//    Question(
//        0b1100000,
//        84, 72
//    ),
//    Question(
//        0b1000110,
//        90, 80
//    )
//)