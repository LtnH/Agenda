package com.example.agenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class EventAdapter(context: Context, events: List<Event>) : ArrayAdapter<Event>(context, 0, events) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertViewVar = convertView
        val event = getItem(position)

        if (convertViewVar == null)
            convertViewVar = LayoutInflater.from(context).inflate(R.layout.event_cell, parent, false)

        val eventCellTV = convertViewVar!!.findViewById<TextView>(R.id.eventCellTV)

        val eventTitle = "${event?.getName()} ${CalendarUtils.formattedTime(event?.getTime())}"
        eventCellTV.text = eventTitle
        return convertViewVar
    }
}