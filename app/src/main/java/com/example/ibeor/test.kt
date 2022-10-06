package com.example.ibeor

import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var progressText: TextView? = null
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set the id for the progressbar and progress text
        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.progress_text)

    }

}
