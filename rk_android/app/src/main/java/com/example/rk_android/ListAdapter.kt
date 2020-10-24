package com.example.rk_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListAdapter : RecyclerView.Adapter<ListAdapter.CourseDateViewHolder>(){

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CourseDateViewHolder, position: Int) {
        val item = data[position]
        holder.setData(item)
        fun onClick(v: View) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseDateViewHolder {
        return CourseDateViewHolder.from(parent)
    }

    class CourseDateViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewRow: TextView = itemView.findViewById(R.id.date_course_s)

        fun setData(item: String) {
            textViewRow.text = item
        }

        companion object {
            fun from(parent: ViewGroup) : CourseDateViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_items, parent, false)
                return CourseDateViewHolder(view)
            }
        }
    }
}