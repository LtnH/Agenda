package com.example.agenda

import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import com.google.android.gms.maps.MapView
import java.time.LocalTime

class EventEditActivity : AppCompatActivity() {
    private lateinit var eventNameET: EditText
    private lateinit var eventDate: DatePicker
    private lateinit var eventTime: TimePicker

    private lateinit var mapView: MapView
    private lateinit var eventDetailActivity: EventDetailsActivity

    private var time: LocalTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_edit)
        eventDetailActivity = EventDetailsActivity()
        initWidgets()
        time = LocalTime.now()
        eventDate.updateDate(CalendarUtils.selectedDate!!.year, CalendarUtils.selectedDate!!.monthValue, CalendarUtils.selectedDate!!.dayOfMonth)
    }

    private fun initWidgets() {
        eventNameET = findViewById(R.id.eventNameET)
        eventDate = findViewById(R.id.eventDate)
        eventTime = findViewById(R.id.eventTime)
        eventTime.setIs24HourView(true)
        mapView = findViewById(R.id.mapView)
    }

    fun saveEventAction(view: View) {
        val eventName = eventNameET.text.toString()
        val newEvent = Event(eventName, LocalDate.of(eventDate.year, eventDate.month, eventDate.dayOfMonth), LocalTime.of(eventTime.hour, eventTime.minute))
        Event.eventsList.add(newEvent)
        finish()
    }
}