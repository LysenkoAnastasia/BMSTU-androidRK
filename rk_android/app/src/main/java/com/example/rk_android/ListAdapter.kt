package com.example.rk_android
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<ListAdapter.MovieViewHolder>() {
    var dataF = listOf<MainActivity.Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount() = dataF.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item : MainActivity.Movie = dataF[position]
        holder.setData(item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    class MovieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.name)

        fun setData(item: MainActivity.Movie) {
            textView.text = item.name

        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item, parent, false)

                return MovieViewHolder(view)
            }
        }
    }
}