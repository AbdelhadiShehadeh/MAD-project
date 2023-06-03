
package com.example.quizapp

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.IBinder

class YouTubeService: Service() {

    inner class YouTubeServiceBinder : Binder() {
        fun getService(): YouTubeService = this@YouTubeService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return YouTubeServiceBinder()
    }

    fun startYouTubeService() {
        // Open YouTube link
        val youtubeLink = "https://www.youtube.com/watch?v=F8_cGGOptD4"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

        // Stop the service
        stopSelf()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Return START_NOT_STICKY as the service is not expected to be restarted automatically
        return START_NOT_STICKY
    }
}

