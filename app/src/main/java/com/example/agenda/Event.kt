package com.example.agenda

import java.time.LocalDate
import java.time.LocalTime

class Event(private var name: String, private var date: LocalDate?, private var time: LocalTime?) {

    companion object {
        val eventsList: ArrayList<Event> = ArrayList()

        // Additional method to get events for a specific date
        fun eventsForDate(date: LocalDate?): ArrayList<Event> {
            val events = ArrayList<Event>()

            for (event in eventsList) {
                if (event.getDate() == date)
                    events.add(event)
            }

            return events
        }

        fun eventsForDateAndTime(date: LocalDate?, time: LocalTime): ArrayList<Event> {
            val events = ArrayList<Event>()

            for (event in eventsList) {
                val eventHour = event.time?.hour
                val cellHour = time.hour
                if (event.date == date && eventHour == cellHour) {
                    events.add(event)
                }
            }

            return events
        }
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDate(): LocalDate? {
        return date
    }

    fun setDate(date: LocalDate) {
        this.date = date
    }

    fun getTime(): LocalTime? {
        return time
    }

    fun setTime(time: LocalTime) {
        this.time = time
    }
}
