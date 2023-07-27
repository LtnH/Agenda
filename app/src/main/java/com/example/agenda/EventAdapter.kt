package com.example.agenda

import android.content.Context
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.Locale

class EventAdapter(context: Context, events: List<Event>) :
    ArrayAdapter<Event>(context, 0, events) {

    private val geocoder = Geocoder(context, Locale.getDefault())

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val event = getItem(position)

        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.event_cell, parent, false)
        }

        val eventCellTV = itemView?.findViewById<TextView>(R.id.eventCellTV)

        val addressText = event?.getLoc()?.let {
            val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                addresses[0].getAddressLine(0)
            } else {
                "Location not available"
            }
        } ?: "Location not available"

        val eventTitle = "${event?.getName()} ${CalendarUtils.formattedTime(event?.getTime())} $addressText"
        eventCellTV?.text = eventTitle

        return itemView!!
    }
}
