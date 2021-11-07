package com.example.pertemuan9_2_71190468

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){
    var sp: SharedPreferences? = null
    var spEdit: SharedPreferences.Editor? = null

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        sp = getSharedPreferences("database", Context.MODE_PRIVATE)
        spEdit = sp?.edit()

        if(sp?.getBoolean("isLogin", false) == true){

            setContentView(R.layout.activity_homepage)
            val edtText = findViewById<TextView>(R.id.editText)
            edtText.setText("Hallo " + sp!!.getString("user","admin"))
            val bahasa = findViewById<Spinner>(R.id.spinnBhs)
            val adapter = ArrayAdapter.createFromResource(this,R.array.list_bahasa,R.layout.support_simple_spinner_dropdown_item)
            bahasa.adapter = adapter
            bahasa.setSelection(sp!!.getInt("bahasa",1))

            bahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    spEdit?.putInt("bahasa",position)
                    spEdit?.commit()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

            val ukuran = findViewById<EditText>(R.id.ukuran)
            ukuran.setText(sp!!.getString("ukuran","10"))

            ukuran.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    spEdit?.putString("ukuran",s.toString())
                    spEdit?.commit()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
        else{
            setContentView(R.layout.activity_main)
            val username = findViewById<EditText>(R.id.userName)
            val password = findViewById<EditText>(R.id.password)
            val btnLogin = findViewById<Button>(R.id.buttonLogin)

            btnLogin.setOnClickListener {
                if(username.text.toString() == "admin" && password.text.toString() == "1234"){
                    spEdit?.putBoolean("isLogin",true)
                    spEdit?.putString("user",username.text.toString())
                    spEdit?.commit()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}