package com.elh.don.genq

import android.content.Context

class MainQDatabaseFacade private constructor(context: Context) {
    companion object {
        fun getInstance(context: Context): MainQDatabaseFacade = MainQDatabaseFacade(context)
    }
    private val database = MainQDatabaseHelper.getInstance(context).readableDatabase
}