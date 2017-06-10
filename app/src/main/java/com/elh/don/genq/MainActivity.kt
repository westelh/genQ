package com.elh.don.genq

import android.content.Intent
import android.database.CursorIndexOutOfBoundsException
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.TextView
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val list = listOf(1, 2, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(find(R.id.toolbar))

        find<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(applicationContext, QuizTable::class.java))
        }

        find<DrawerLayout>(R.id.drawer_layout).apply {
            addDrawerListener(ActionBarDrawerToggle(this@MainActivity, this, find(R.id.toolbar),
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close).apply {
                syncState()
            })
        }

        find<NavigationView>(R.id.nav_view).setNavigationItemSelectedListener(this@MainActivity)

        find<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CellAdapter(list)
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    inner class CellAdapter <out T> (val data: List<T>) : RecyclerView.Adapter<CellAdapter<T>.ViewHolder>() {
        val database = MainQDatabaseFacade.getInstance(this@MainActivity)
        override fun getItemCount(): Int = database.queryTitles().exec { columnCount }
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder
                = ViewHolder(LayoutInflater.from(this@MainActivity).inflate(R.layout.recycler_cell, parent, false))
        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            database.queryTitles().exec {
                moveToNext()
                holder?.apply {
                    try {
                        text.text = getString(0)
                    } catch (e: CursorIndexOutOfBoundsException) {
                        Log.e("MainQDatabase", "Required entry does not exist! (index:${this@exec.position})")
                        text.text = getString(R.string.alt_msg_failing_load_data)
                    }
                }
            }
        }
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text = itemView.findViewById(R.id.recycler_cell_text) as TextView
        }
    }
}

