package com.example.pertemuan10_71190468

import android.provider.BaseColumns

class DatabaseContract {
    object Penduduk: BaseColumns{
        const val NAMA_TABLE = "penduduk"
        const val COLUMN_NAMA = "nama"
        const val COLUMN_USIA = "usia"

        const val SQL_CREATE_TABLE = "CREATE TABLE ${NAMA_TABLE} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${COLUMN_NAMA} TEXT,"+
                "${COLUMN_USIA} INTEGER"+
                ")"

        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${NAMA_TABLE}"
    }
}