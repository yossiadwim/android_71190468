package com.example.pertemuan5_71190468

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("username")

        val opening = findViewById<TextView>(R.id.opening)
        opening.text = "Selamat Datang $username"
    }
}