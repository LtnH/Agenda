package com.example.agenda

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.util.ArrayList

class CalendarAdapter(private val days: ArrayList<LocalDate?>, private val onItemListener: OnItemListener) :
    RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)

        // Set layout params for the view
        val layoutParams = view.layoutParams
        if (days.size > 15) { // month view
            layoutParams.height = (parent.height * 0.1).toInt()
        } else { // week view
            layoutParams.height = (parent.height * 0.6).toInt()
        }
        view.layoutParams = layoutParams

        return CalendarViewHolder(view, onItemListener, days)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = days[position]

        holder.dayOfMonth.text = date?.dayOfMonth.toString()

        if (date == CalendarUtils.selectedDate) {
            holder.parentView.setBackgroundResource(R.drawable.baground_cell_select)
        }

        if (date?.month == CalendarUtils.selectedDate?.month) {
            holder.dayOfMonth.setTextColor(Color.BLACK)
        } else {
            holder.dayOfMonth.setTextColor(Color.GRAY)
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, date: LocalDate?)
    }
}
