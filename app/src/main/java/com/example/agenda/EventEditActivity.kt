package com.example.agenda

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.time.LocalDate
import java.time.LocalTime

class EventEditActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var eventNameET: EditText
    private lateinit var eventDate: DatePicker
    private lateinit var eventTime: TimePicker
    private lateinit var mapView: SupportMapFragment
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation: LatLng? = null

    private var time: LocalTime? = null

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_edit)

        initWidgets()
        time = LocalTime.now()
        eventDate.updateDate(
            CalendarUtils.selectedDate!!.year,
            CalendarUtils.selectedDate!!.monthValue - 1,
            CalendarUtils.selectedDate!!.dayOfMonth
        )
        mapView = supportFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment
        mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    currentLocation = LatLng(location.latitude, location.longitude)
                    if (googleMap != null) {
                        updateMapLocation(currentLocation!!)
                    }
                    fusedLocationClient.removeLocationUpdates(this)  // Stop location updates after receiving first location.
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    private fun initWidgets() {
        eventNameET = findViewById(R.id.eventNameET)
        eventDate = findViewById(R.id.eventDate)
        eventTime = findViewById(R.id.eventTime)
        eventTime.setIs24HourView(true)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        if (currentLocation != null) {
            updateMapLocation(currentLocation!!)
        }
    }

    private fun updateMapLocation(location: LatLng) {
        googleMap.clear()  // clear previous markers
        googleMap.addMarker(MarkerOptions().position(location).title("My Location"))
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
        val newEvent = Event(
            eventName, LocalDate.of(eventDate.year, eventDate.month + 1, eventDate.dayOfMonth),
            LocalTime.of(eventTime.hour, eventTime.minute), currentLocation!!
        )
        Event.eventsList.add(newEvent)
        finish()
    }
}