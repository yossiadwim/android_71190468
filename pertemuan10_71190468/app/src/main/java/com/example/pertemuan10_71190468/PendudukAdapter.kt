package com.example.pertemuan10_71190468

import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PendudukAdapter(var listPenduduk: ArrayList<String>, var db: SQLiteDatabase?) :RecyclerView.Adapter<PendudukAdapter.PendudukHolder>(){
    class PendudukHolder(val v: View, val db: SQLiteDatabase?): RecyclerView.ViewHolder(v){
        fun bindView(data: String){
            val textInfo = v.findViewById<TextView>(R.id.textInfo)
            textInfo.setText(data)

            val btnHapus = v.findViewById<Button>(R.id.buttonHapus)
            btnHapus.setOnClickListener {
                val selection = "${DatabaseContract.Penduduk.COLUMN_NAMA} = ?"
                val dataArray = data.split(" (")
                val selectionArgs = arrayOf(dataArray[0])
                db!!.delete(DatabaseContract.Penduduk.NAMA_TABLE,selection,selectionArgs)

                val main: MainActivity? = null
                main?.reloadData()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendudukHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table_penduduk,parent,false)
        return PendudukHolder(view,db)
    }

    override fun onBindViewHolder(holder: PendudukHolder, position: Int) {
        holder.bindView(listPenduduk.get(position))
    }

    override fun getItemCount(): Int {
        return listPenduduk.size
    }
}