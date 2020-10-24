package com.example.rk_android

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewF: RecyclerView
    private lateinit var viewAdapterF: RecyclerView.Adapter<*>
    private lateinit var viewManagerF: RecyclerView.LayoutManager
    public var is_bool = 0



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val list2: List<String> = listOf("24/10/2020", "23/10/2020", "22/10/2020",
            "21/10/2020", "20/10/2020", "19/10/2020",
            "18/10/2020", "17/10/2020", "16/10/2020"
        )


        viewManagerF = LinearLayoutManager(this)
        viewAdapterF = ListAdapter()
        (viewAdapterF as com.example.rk_android.ListAdapter).data = list2
        recyclerViewF = findViewById<RecyclerView>(R.id.recycler_course_date).apply {
            setHasFixedSize(true)
            layoutManager = viewManagerF
            adapter = viewAdapterF
        }
    }

    fun openMe(view: View) {
        val myToast = Toast.makeText(this, "Курсы", Toast.LENGTH_SHORT)
        myToast.show()
        click(text_count)
    }

    fun click(view: View) {
        val countString = text_count.text.toString()

        var count: Int = Integer.parseInt(countString.toString())
        count++
        text_count.text = count.toString();
    }
}