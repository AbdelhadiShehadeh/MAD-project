package com.example.quizapp

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.data.QuizContract
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        val userName=intent.getStringExtra(setData.name)
        val score=intent.getStringExtra(setData.score)
        val totalQuestion=intent.getStringExtra("total size")

        val values = ContentValues().apply {
            put(QuizContract.ScoreEntry.COLUMN_USERNAME, userName)
            put(QuizContract.ScoreEntry.COLUMN_SCORE, score)
        }
        val uri = Uri.parse("content://com.example.quizapp.provider/scores")
        val insertedUri = contentResolver.insert(uri, values)

        congo.text="Congratulations ${userName} !!"
        Score.text="${score} / ${totalQuestion}"
        button.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}