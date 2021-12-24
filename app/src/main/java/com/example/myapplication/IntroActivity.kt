package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class IntroActivity: AppCompatActivity() {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        colorStatusBar()
        setContentView(R.layout.activity_intro)
        val button = findViewById<Button>(R.id.buttonIntro)
        val videoView = findViewById<VideoView>(R.id.videoView)
        val imageView = findViewById<ImageView>(R.id.imageIntro)
        val path = "android.resource://com.example.myapplication/" + R.raw.intro_video
        val uri: Uri = Uri.parse(path)
        videoView.setVideoURI(uri)
        videoView.setOnPreparedListener(OnPreparedListener { mp ->
            mp.start()
            imageView.setVisibility(View.GONE)
        })

        button.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}