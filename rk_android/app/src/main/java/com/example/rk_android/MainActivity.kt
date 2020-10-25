package com.example.rk_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_refresh) {
            updateData()
        }
        if (id == R.id.action_settings) {
            courseSettings()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateData(){

    }

    fun courseSettings(){
        val intent = Intent(this, SettingsActivity::class.java).apply {}
        startActivity(intent)

    }


    data class Movie(val name: String)
}