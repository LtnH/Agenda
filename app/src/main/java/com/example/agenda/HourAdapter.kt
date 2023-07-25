package com.example.agenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.time.LocalTime

class HourAdapter(context: Context, hourEvents: List<HourEvent>) : ArrayAdapter<HourEvent>(context, 0, hourEvents) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertViewVar = convertView
        val event = getItem(position)

        if (convertViewVar == null)
            convertViewVar = LayoutInflater.from(context).inflate(R.layout.hour_cell, parent, false)

        setHour(convertViewVar, event?.time)
        setEvents(convertViewVar, event?.events)

        return convertViewVar!!
    }

    private fun setHour(convertView: View?, time: LocalTime?) {
        val timeTV = convertView?.findViewById<TextView>(R.id.timeTV)
        timeTV?.text = CalendarUtils.formattedShortTime(time)
    }

    private fun setEvents(convertView: View?, events: ArrayList<Event>?) {
        val event1 = convertView?.findViewById<TextView>(R.id.event1)
        val event2 = convertView?.findViewById<TextView>(R.id.event2)
        val event3 = convertView?.findViewById<TextView>(R.id.event3)

        when (events?.size) {
            0 -> {
                if (event1 != null) {
                    hideEvent(event1)
                }
                if (event2 != null) {
                    hideEvent(event2)
                }
                if (event3 != null) {
                    hideEvent(event3)
                }
            }
            1 -> {
                if (event1 != null) {
                    setEvent(event1, events[0])
                }
                if (event2 != null) {
                    hideEvent(event2)
                }
                if (event3 != null) {
                    hideEvent(event3)
                }
            }
            2 -> {
                if (event1 != null) {
                    setEvent(event1, events[0])
                }
                if (event2 != null) {
                    setEvent(event2, events[1])
                }
                if (event3 != null) {
                    hideEvent(event3)
                }
            }
            3 -> {
                if (event1 != null) {
                    setEvent(event1, events[0])
                }
                if (event2 != null) {
                    setEvent(event2, events[1])
                }
                if (event3 != null) {
                    setEvent(event3, events[2])
                }
            }
            else -> {
                if (event1 != null) {
                    setEvent(event1, events?.get(0))
                }
                if (event2 != null) {
                    setEvent(event2, events?.get(1))
                }
                event3?.visibility = View.VISIBLE
                val eventsNotShown = "${events?.size?.minus(2)} More Events"
                event3?.text = eventsNotShown
            }
        }
    }

    private fun setEvent(textView: TextView, event: Event?) {
        textView.text = event?.getName()
        textView.visibility = View.VISIBLE
    }

    private fun hideEvent(tv: TextView) {
        tv.visibility = View.INVISIBLE
    }
}
