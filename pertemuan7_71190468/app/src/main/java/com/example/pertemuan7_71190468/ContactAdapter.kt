package com.example.pertemuan7_71190468

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (val listContactAdapter: ArrayList<Contact>, var context: Context): RecyclerView.Adapter<ContactAdapter.ContactHolder>(){
    class ContactHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bindViewContact(contact: Contact, context: Context){
            view.findViewById<ImageView>(R.id.imgContact).setImageResource(contact.foto)
            view.findViewById<TextView>(R.id.contactName).setText(contact.name)
            view.findViewById<TextView>(R.id.numberContact).setText(contact.contact)
            view.setOnClickListener{
                val intent:Intent = Intent(view.context,DetailContact::class.java)
                intent.putExtra("name",contact.name)
                intent.putExtra("contact",contact.contact)
                intent.putExtra("foto",contact.foto)
                view.context.startActivity(intent)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ContactHolder(v)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bindViewContact(listContactAdapter[position],context)

    }

    override fun getItemCount(): Int {

        return listContactAdapter.size
    }
}