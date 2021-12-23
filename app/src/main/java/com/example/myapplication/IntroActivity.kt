package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.view.WindowManager
import android.os.Bundle
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import android.widget.VideoView

class IntroActivity: AppCompatActivity() {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(1)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.statusBarColor = Color.TRANSPARENT
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