package com.example.rk_android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_currency.*


class secondFragment : Fragment() {
    private var url = "https://min-api.cryptocompare.com/documentation?key=Historical&cat=dataHistoday&api_key=600bf09ff8d82df6383db691c3873eb7fa6ea91651f8a33bfbd90dd2ddafcf4a"

    private var low: String? = null
    private var high: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            high = it.getString("high")
            low = it.getString("low")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainView: View = inflater.inflate(R.layout.fragment_currency, container, false)
        mainView.findViewById<Button>(R.id.open_link).setOnClickListener {
            run {
                openWebPage(url)
            }
        }
        return mainView
//        return inflater.inflate(R.layout.fragment_currency, container, false)
    }



    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lowValue.text = low
        highValue.text = high
    }

}