package com.elh.don.genq

import android.content.Context
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.select

class MainQDatabaseFacade private constructor(val context: Context) {
    companion object {
        fun getInstance(context: Context): MainQDatabaseFacade = MainQDatabaseFacade(context)
    }
    private val database = MainQDatabaseHelper.getInstance(context).readableDatabase
    fun queryTitles(): SelectQueryBuilder {
        return database.select(context.getString(R.string.questions_group_table_name), "title")
    }
}