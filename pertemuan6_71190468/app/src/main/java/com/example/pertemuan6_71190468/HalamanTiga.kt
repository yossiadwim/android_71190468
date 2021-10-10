package com.example.pertemuan6_71190468

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HalamanTiga : AppCompatActivity(R.layout.tiga_halaman) {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.tiga_halaman)
    }
}