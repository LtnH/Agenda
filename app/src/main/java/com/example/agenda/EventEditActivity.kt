package com.example.agenda

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.MapView
import java.time.LocalTime

class EventEditActivity : AppCompatActivity() {
    private lateinit var eventNameET: EditText
    private lateinit var eventDateTV: TextView
    private lateinit var eventTimeTV: TextView
    private lateinit var mapView: MapView
    private lateinit var eventDetailActivity: EventDetailsActivity

    private var time: LocalTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_edit)
        eventDetailActivity = EventDetailsActivity()
        initWidgets()
        time = LocalTime.now()
        eventDateTV.text = "Date: ${CalendarUtils.formattedDate(CalendarUtils.selectedDate)}"
        eventTimeTV.text = "Time: ${CalendarUtils.formattedTime(time)}"
    }

    private fun initWidgets() {
        eventNameET = findViewById(R.id.eventNameET)
        eventDateTV = findViewById(R.id.eventDateTV)
        eventTimeTV = findViewById(R.id.eventTimeTV)
        mapView = findViewById(R.id.mapView)
    }

    fun saveEventAction(view: View) {
        val eventName = eventNameET.text.toString()
        val newEvent = Event(eventName, CalendarUtils.selectedDate, time)
        Event.eventsList.add(newEvent)
        finish()
    }
}