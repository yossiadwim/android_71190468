package com.example.pertemuan10_71190468

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.database.getStringOrNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var dbHelper: SQLiteOpenHelper? = null
    var db: SQLiteDatabase? = null
    val listPenduduk = ArrayList<String>()
    var adapter: PendudukAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        db = dbHelper?.writableDatabase

        val nama = findViewById<EditText>(R.id.nama)
        val usia = findViewById<EditText>(R.id.usia)
        val hasilPencarian = findViewById<TextView>(R.id.hasilPencarian)
        val btnSimpan = findViewById<Button>(R.id.buttonSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)


        btnSimpan.setOnClickListener {
            val values = ContentValues().apply {
                put(DatabaseContract.Penduduk.COLUMN_NAMA, nama.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_USIA, usia.text.toString())
            }
            db?.insert(DatabaseContract.Penduduk.NAMA_TABLE, null, values)
            nama.setText("")
            usia.setText("")
            reloadData()
        }

        btnCari.setOnClickListener {
            val columns = arrayOf(
                BaseColumns._ID,
                DatabaseContract.Penduduk.COLUMN_NAMA,
                DatabaseContract.Penduduk.COLUMN_USIA
            )
            val selection = "${DatabaseContract.Penduduk.COLUMN_NAMA} = ?"
            val selectionArgs = arrayOf(nama.text.toString())
            val cursor = db?.query(
                DatabaseContract.Penduduk.NAMA_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            with(cursor!!) {
                while (moveToNext()) {
                    val dataNama =
                        getStringOrNull(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAMA))
                    val dataUsia =
                        getStringOrNull(getColumnIndex(DatabaseContract.Penduduk.COLUMN_USIA))

                    hasilPencarian.setText(dataNama+" "+dataUsia)
//                    nama.setText(dataNama)
//                    usia.setText(dataUsia)


                }
            }
        }
        val recyclePenduduk = findViewById<RecyclerView>(R.id.rcyPenduduk)
        recyclePenduduk.layoutManager = LinearLayoutManager(this)
        val adapter = PendudukAdapter(listPenduduk, db)
        recyclePenduduk.adapter = adapter
        reloadData()
    }


    fun reloadData(){
        listPenduduk.clear()
        val columns = arrayOf(
            BaseColumns._ID,
            DatabaseContract.Penduduk.COLUMN_NAMA,
            DatabaseContract.Penduduk.COLUMN_USIA
        )

        val cursor = db?.query(
            DatabaseContract.Penduduk.NAMA_TABLE,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor!!){
            while (moveToNext()){
                val nama = getStringOrNull(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAMA))
                val usia = getStringOrNull(getColumnIndex(DatabaseContract.Penduduk.COLUMN_USIA))

                listPenduduk.add("${nama} (${usia})")
            }
        }
    }
}