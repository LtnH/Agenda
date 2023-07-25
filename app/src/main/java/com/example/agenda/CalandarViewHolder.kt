package com.example.agenda

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.util.ArrayList

class CalendarViewHolder(itemView: View, private val onItemListener: CalendarAdapter.OnItemListener, private val days: ArrayList<LocalDate?>) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val parentView: View = itemView.findViewById(R.id.parentView)
    val dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        Log.v("test", bindingAdapterPosition.toString())
        onItemListener.onItemClick(bindingAdapterPosition, days[bindingAdapterPosition])
    }
}
