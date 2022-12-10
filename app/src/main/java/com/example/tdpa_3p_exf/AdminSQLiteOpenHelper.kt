package com.example.tdpa_3p_exf

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper
    (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context,name,factory,version)
{
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE alumnos (matricula int primary key, " +
                "nombre text," +
                "materia text," +
                "cal1 real," +
                "cal2 real)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}