package com.example.pertemuan6_71190468

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentB: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.b_fragment,container, false)
        val btn = v.findViewById<Button>(R.id.btnFragB)
        btn.setOnClickListener {
            Toast.makeText(context,"Halaman 3",Toast.LENGTH_SHORT).show()
            val intent = Intent(context,HalamanTiga::class.java)
            startActivity(intent)
        }
        return v
    }
}