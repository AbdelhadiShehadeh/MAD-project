package com.example.quizapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private var youtubeService: YouTubeService? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as YouTubeService.YouTubeServiceBinder
            youtubeService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            youtubeService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        button.setOnClickListener {
            if(input.text.toString().isEmpty())
            {
                Toast.makeText(this,"Enter Your Name",Toast.LENGTH_SHORT).show()
            }
            else{
                var intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("${setData.name}",input.text.toString())
                startActivity(intent)
                finish()
            }
        }




        val kotlinB: Button = findViewById(R.id.kotlinB)
        kotlinB.setOnClickListener {
            youtubeService?.startYouTubeService()
        }
    }

    override fun onStart() {
        super.onStart()
        val serviceIntent = Intent(this, YouTubeService::class.java)
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
    }
}