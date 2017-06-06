package com.elh.don.genq

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class QuizTable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_table)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = super.onOptionsItemSelected(item).apply {
        when(item?.itemId) {
            R.id.home -> startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}
