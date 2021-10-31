package com.example.pertemuan9_71190468

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity

class Send : AppCompatActivity(R.layout.send_layout) {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.send_layout)
    }

}