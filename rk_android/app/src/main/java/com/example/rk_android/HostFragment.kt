package com.example.rk_android

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rk_android.databinding.FragmentHostBinding
import kotlinx.android.synthetic.main.fragment_host.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class HostFragment: Fragment() {

    private var fragmentFirstBinding: FragmentHostBinding? = null
    private lateinit var sharedPreferences: SharedPreferences
    private var APP_PREFERENCES = "mysettings"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        var checkCryptoCurrency = sharedPref.getString("cryptoCurrency", "")
        if (checkCryptoCurrency != null) {
            if (checkCryptoCurrency.isEmpty()) {
                checkCryptoCurrency = "BTC"
                editor.putString("cryptoCurrency", "BTC")
                editor.apply()
            }
        }

        if (checkCryptoCurrency == "BTC") {
            radio_btc.isChecked = true
        } else if (checkCryptoCurrency == "ETH") {
            radio_eth.isChecked = true
        } else {
            radio_xrp.isChecked = true
        }

        fragmentFirstBinding = FragmentHostBinding.bind(view)

        val recyclerView: RecyclerView = fragmentFirstBinding!!.recyclerCourseDate
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
        recyclerView.adapter = ListAdapter(generalFakeValues())
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = group.findViewById(checkedId)
            editor.putString("cryptoCurrency", radio.text.toString())
            editor.apply()
            recyclerView.adapter = ListAdapter(generalFakeValues())
        }
    }

    @Volatile
    var values = mutableListOf<CourseRow>()

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("CheckResult")
    private fun generalFakeValues(): List<CourseRow> {
        values = mutableListOf<CourseRow>()
        val sharedPref = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        var strUrl: String = "https://min-api.cryptocompare.com/data/v2/histoday?"
        var currency: String? = sharedPreferences.getString("currencyList", "RUB")
        var days: String? = sharedPreferences.getString("days", "10")
        strUrl += "fsym="
        strUrl += sharedPref.getString("cryptoCurrency", "BTC")
        strUrl += "&tsym="
        strUrl += currency
        strUrl += "&limit="
        strUrl += days
        val url =
            URL(strUrl)
        var s: StringBuilder = java.lang.StringBuilder("")
        val job = thread(start = true) {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"  // optional default is GET

                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        s.append(line)
                    }
                }
            }
        }
        job.join()
        val jsonObject = JSONObject(s.toString())
        val jsonArray = jsonObject.getJSONObject("Data").getJSONArray("Data")
        val format = SimpleDateFormat("dd.MM.yyyy")
        for (jsonIndex in 0 until jsonArray.length()) {

            var closePrice: String = jsonArray.getJSONObject(jsonIndex).getString("close")
            var date = Date(jsonArray.getJSONObject(jsonIndex).getLong("time") * 1000)
            val high: String = jsonArray.getJSONObject(jsonIndex).getString("high")
            val low: String = jsonArray.getJSONObject(jsonIndex).getString("low")
            val open: String = jsonArray.getJSONObject(jsonIndex).getString("open")
            val elem = currency?.let { CourseRow(format.format(date), closePrice, it, high, low, open) }
            if (elem != null) {
                values.add(elem)
            }
        }
        return values
    }
}