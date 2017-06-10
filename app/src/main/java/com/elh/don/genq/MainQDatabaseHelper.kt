package com.elh.don.genq

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MainQDatabaseHelper private constructor(val context: Context) : ManagedSQLiteOpenHelper(context, context.getString(R.string.database_name)) {
    companion object {
        private val obj : MainQDatabaseHelper? = null
        fun getInstance(context: Context): MainQDatabaseHelper = obj?: MainQDatabaseHelper(context)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(context.getString(R.string.questions_group_table_name), true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                "title" to TEXT)
        db?.insert(context.getString(R.string.questions_group_table_name), null, ContentValues().apply {
            put("title", "サンプル")
            put("id", 1)
        })
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}