package com.example.pertemuan7_71190468

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailContact : AppCompatActivity(R.layout.detail_contact) {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.detail_contact)

        val nama = intent.getStringExtra("name")
        val nomor = intent.getStringExtra("contact")
        val foto = intent.getIntExtra("foto",0)

        val tampilNama = findViewById<TextView>(R.id.tampilNama)
        val tampilNomor = findViewById<TextView>(R.id.tampilNomor)
        val tampilFoto = findViewById<ImageView>(R.id.foto)

        tampilNama.text = nama
        tampilNomor.text = nomor
        tampilFoto.setImageResource(foto)
    }
}