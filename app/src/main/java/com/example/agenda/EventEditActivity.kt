package com.example.agenda

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import java.time.LocalDate
import com.google.android.gms.maps.MapView
import java.time.LocalTime
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class EventEditActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var eventNameET: EditText
    private lateinit var eventDate: DatePicker
    private lateinit var eventTime: TimePicker
    private lateinit var mapView: SupportMapFragment
    private lateinit var googleMap: GoogleMap

    private lateinit var eventDetailActivity: EventDetailsActivity
    private var time: LocalTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_edit)

        eventDetailActivity = EventDetailsActivity()
        initWidgets()
        time = LocalTime.now()
        eventDate.updateDate(CalendarUtils.selectedDate!!.year, CalendarUtils.selectedDate!!.monthValue, CalendarUtils.selectedDate!!.dayOfMonth)
        mapView = supportFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment
        mapView.getMapAsync(this)

    }

    private fun initWidgets() {
        eventNameET = findViewById(R.id.eventNameET)
        eventDate = findViewById(R.id.eventDate)
        eventTime = findViewById(R.id.eventTime)
        eventTime.setIs24HourView(true)
    }

    override fun onMapReady(map: GoogleMap) {
        Log.wtf("wtf", "eho")
        googleMap = map
        val location = LatLng(48.858844, 2.294351)
        googleMap.addMarker(MarkerOptions().position(location).title("Tour Eiffel"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    fun saveEventAction(view: View) {
        val eventName = eventNameET.text.toString()
        val newEvent = Event(eventName, LocalDate.of(eventDate.year, eventDate.month, eventDate.dayOfMonth), LocalTime.of(eventTime.hour, eventTime.minute))
        Event.eventsList.add(newEvent)
        finish()
    }
}