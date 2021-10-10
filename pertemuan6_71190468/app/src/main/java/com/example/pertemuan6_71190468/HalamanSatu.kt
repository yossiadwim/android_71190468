package com.example.pertemuan6_71190468

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HalamanSatu : AppCompatActivity(R.layout.satu_halaman) {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.satu_halaman)
    }
}