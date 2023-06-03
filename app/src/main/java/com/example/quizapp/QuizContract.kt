package com.example.quizapp.data

import android.provider.BaseColumns

object QuizContract {

    object ScoreEntry : BaseColumns {
        const val TABLE_NAME = "scores"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_SCORE = "score"
    }
}
