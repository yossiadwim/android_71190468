package com.example.pertemuan6_71190468

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentC: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.c_fragment,container,false)
        val btn = v.findViewById<Button>(R.id.btnFragC)
        btn.setOnClickListener {
            Toast.makeText(context,"Halaman 1", Toast.LENGTH_SHORT).show()
            val intent = Intent(context,HalamanSatu::class.java)
            startActivity(intent)
        }
        return v
    }
}