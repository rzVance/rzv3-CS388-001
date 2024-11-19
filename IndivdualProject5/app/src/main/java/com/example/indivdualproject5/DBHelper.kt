package com.example.indivdualproject5

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                WATER_AMOUNT + " INTEGER," +
                NOTES + " TEXT, " +
                DAYOFYEAR + " INTEGER)")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun addWater(waterAmount : String, notes : String){
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(WATER_AMOUNT, waterAmount)
        values.put(NOTES, notes)
        values.put(DAYOFYEAR, dayOfYear)

        Log.i("DB_VAL", values.toString())

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getWater(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }

    companion object{
        private val DATABASE_NAME = "BitFit"
        private val DATABASE_VERSION = 3

        val TABLE_NAME = "water_table"

        val ID = "id"
        val WATER_AMOUNT = "water_amount"
        val NOTES = "notes"
        val DAYOFYEAR = "day_of_the_year"
    }
}