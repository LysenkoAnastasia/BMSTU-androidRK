package com.example.rk_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_second.*

private const val URL_VALUE = "url"

class SecondHostFragment: Fragment() {
    private var low: String? = null
    private var high: String? = null
    private var open: String? = null
    private var close: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            open = it.getString("open")
            close = it.getString("close")
            high = it.getString("high")
            low = it.getString("low")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lowValue.text = low
        highValue.text = high
        openValue.text = open
        closeValue.text = close
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SecondHostFragment().apply {
            }
    }
}