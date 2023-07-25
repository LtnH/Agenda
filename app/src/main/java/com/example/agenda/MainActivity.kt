package com.example.agenda

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.CalendarUtils.daysInMonthArray
import com.example.agenda.CalendarUtils.monthYearFromDate
import java.time.LocalDate

class MainActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {
    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWidgets()
        CalendarUtils.selectedDate = LocalDate.now()
        setMonthView()
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monthYearTV)
    }

    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(CalendarUtils.selectedDate)
        val daysInMonth = daysInMonthArray(CalendarUtils.selectedDate)

        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
    }

    fun previousMonthAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.minusMonths(1)
        setMonthView()
    }

    fun nextMonthAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.plusMonths(1)
        setMonthView()
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        if (date != null) {
            CalendarUtils.selectedDate = date
            setMonthView()
        }
    }

    fun weeklyAction(view: View) {
        startActivity(Intent(this, WeekViewActivity::class.java))
    }
}
