package com.example.pertemuan9_71190468

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity

class Profile: AppCompatActivity(R.layout.profile_layout) {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.profile_layout)
    }
}