package com.example.rk_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class ListAdapter (private val data: List<CourseRow>): RecyclerView.Adapter<ListAdapter.CourseDateViewHolder>(){


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CourseDateViewHolder, position: Int) {
        val item: CourseRow = data[position]
        holder.setData(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseDateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CourseDateViewHolder (inflater, parent)
    }


    class CourseDateViewHolder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
        private val parent = parent
        private var mTextViewDate: TextView?  = null
        private var mTextViewClosePrice: TextView?  = null
        private var mTextViewCurrency: TextView?  = null

        init {
            mTextViewDate = itemView.findViewById(R.id.date_course)
            mTextViewClosePrice = itemView.findViewById(R.id.close_course)
            mTextViewCurrency = itemView.findViewById(R.id.currency)
        }

        fun setData(row: CourseRow) {
            mTextViewDate?.text = row.date
            mTextViewClosePrice?.text = row.closeCourse
            mTextViewCurrency?.text = row.currency
            itemView.setOnClickListener {
                val itemInfo = SecondHostFragment()
                val bundle = Bundle()
                //itemInfo.arguments = bundle
                bundle.putString("open", row.open)
                bundle.putString("close", row.closeCourse)
                bundle.putString("high", row.high)
                bundle.putString("low", row.low)
                //parent.findNavController().navigate(R.id.action_hostFragment_to_secondFragment2, bundle)
            }
        }
    }

//    class CourseDateViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
//        val textViewRow: TextView = itemView.findViewById(R.id.date_course_s)
//
//        fun setData(item: String) {
//            textViewRow.text = item
//        }
//
//        companion object {
//            fun from(parent: ViewGroup) : CourseDateViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.list_items, parent, false)
//                return CourseDateViewHolder(view)
//            }
//        }
//    }
}