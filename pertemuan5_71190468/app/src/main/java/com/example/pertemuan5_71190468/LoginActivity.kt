package com.example.pertemuan5_71190468

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val btnlogin = findViewById<Button>(R.id.btnlogin)

        btnlogin.setOnClickListener {
            if (password.text.toString().equals("12345")){
                toast("Login Berhasil")
                val intent = Intent(this,HomeActivity:: class.java)
                intent.putExtra("username",username.text.toString())
                startActivity(intent)
            }
            else{
               toast("Login Gagal")
            }
        }
    }

    fun toast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}