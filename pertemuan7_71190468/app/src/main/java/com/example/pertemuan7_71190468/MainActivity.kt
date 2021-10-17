package com.example.pertemuan7_71190468

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = arrayListOf<Contact>()
        listContact.add(Contact("Yossia","081273422807", R.mipmap.kontak_foreground))
        listContact.add(Contact("Susi","08123456789", R.mipmap.kontak_foreground))
        listContact.add(Contact("Chandra","0887654321", R.mipmap.kontak_foreground))
        listContact.add(Contact("Dea","08987634213", R.mipmap.kontak_foreground))
        listContact.add(Contact("Daniel","0812233445", R.mipmap.kontak_foreground))
        listContact.add(Contact("Ivan","08112233445", R.mipmap.kontak_foreground))
        listContact.add(Contact("Tandiri","08877665544", R.mipmap.kontak_foreground))
        listContact.add(Contact("Patrick","08234546576", R.mipmap.kontak_foreground))

        val rcyContact = findViewById<RecyclerView>(R.id.rcyContact)
        rcyContact.layoutManager = LinearLayoutManager(this)
        val a = ContactAdapter(listContact,this)
        rcyContact.adapter = a
    }

}