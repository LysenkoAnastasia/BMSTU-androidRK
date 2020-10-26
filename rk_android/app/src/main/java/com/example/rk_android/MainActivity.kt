package com.example.rk_android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.rk_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var url = "https://min-api.cryptocompare.com/documentation?key=Historical&cat=dataHistoday&api_key=600bf09ff8d82df6383db691c3873eb7fa6ea91651f8a33bfbd90dd2ddafcf4a"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = this.findNavController(R.id.my_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val item_id: Int = item.getItemId()
        if (item_id == R.id.action_settings) {
            val intent = Intent(this, SettingsActivity::class.java).apply {}
            startActivity(intent)
            return true
        }

        else if (item_id == R.id.menu_refresh) {
            onRestart()
//            val refresh = Intent(this, ListFragment::class.java)
//            startActivity(refresh) //Start the same Activity
//
////            finish() //finish Activity.
//
//            return true
        }

        else if (item_id == R.id.action_open_web_page) {
            openWebPage(url)
            return true
        } else if (item_id == R.id.action_show_map) {
            val uriBuilder = Uri.Builder()
            val addressUri = uriBuilder
                .scheme("geo")
                .path("47.6,-122.3")
                .build()
            showMap(addressUri)
            return true
        } else if (item_id == R.id.action_open_wifi_setting) {
            openWifiSettings()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    fun showMap(geoLocation: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    fun openWifiSettings() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onRestart() {
        recreate()
        super.onRestart()
    }
}