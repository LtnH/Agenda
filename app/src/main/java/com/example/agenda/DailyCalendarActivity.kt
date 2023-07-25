package com.example.agenda

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.CalendarUtils.selectedDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*
import kotlin.collections.ArrayList

class DailyCalendarActivity : AppCompatActivity() {

    private lateinit var monthDayText: TextView
    private lateinit var dayOfWeekTV: TextView
    private lateinit var hourListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_calendar)
        initWidgets()
    }

    private fun initWidgets() {
        monthDayText = findViewById(R.id.monthDayText)
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV)
        hourListView = findViewById(R.id.hourListView)
    }

    override fun onResume() {
        super.onResume()
        setDayView()
    }

    private fun setDayView() {
        monthDayText.text = CalendarUtils.monthDayFromDate(selectedDate)
        val dayOfWeek = selectedDate?.dayOfWeek?.getDisplayName(TextStyle.FULL, Locale.getDefault())
        dayOfWeekTV.text = dayOfWeek
        setHourAdapter()
    }

    private fun setHourAdapter() {
        val hourAdapter = HourAdapter(applicationContext, hourEventList())
        hourListView.adapter = hourAdapter
    }

    private fun hourEventList(): ArrayList<HourEvent> {
        val list = ArrayList<HourEvent>()

        for (hour in 0 until 24) {
            val time = LocalTime.of(hour, 0)
            val events = Event.eventsForDateAndTime(selectedDate, time)
            val hourEvent = HourEvent(time, events)
            list.add(hourEvent)
        }

        return list
    }

    fun previousDayAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.minusDays(1)
        setDayView()
    }

    fun nextDayAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.plusDays(1)
        setDayView()
    }

    fun newEventAction(view: View) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }
}