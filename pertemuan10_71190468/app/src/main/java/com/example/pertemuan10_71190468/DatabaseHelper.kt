package com.example.pertemuan10_71190468

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION){

    companion object{
        const val DATABASE_NAME = "Database"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DatabaseContract.Penduduk.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}