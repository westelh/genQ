package com.elh.don.genq

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import org.jetbrains.anko.find

class QuizTable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_table)
        setSupportActionBar(find(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = super.onOptionsItemSelected(item).apply {
        Log.i("QuizTable", "Called onOptionsItemSelected. That is ${item?.itemId?:"unknown"}")
        when(item?.itemId) {
            android.R.id.home -> finish()
            else -> Toast.makeText(applicationContext, "Uncaught Event on OptionsItemSelected!! (id:${item?.itemId})", Toast.LENGTH_LONG).show()
        }
    }
}
