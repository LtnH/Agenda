package com.example.agenda

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EventViewActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var eventLocation: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_view)

        val eventName = intent.getStringExtra("eventName")
        val eventDate = intent.getStringExtra("eventDate")
        val eventTime = intent.getStringExtra("eventTime")
        eventLocation = intent.getParcelableExtra<LatLng>("eventLocation")!!

        // Afficher les détails de l'événement dans les vues appropriées
        val eventNameTextView = findViewById<TextView>(R.id.eventNameET)
        val eventDateTextView = findViewById<TextView>(R.id.eventDate)
        val eventTimeTextView = findViewById<TextView>(R.id.eventTime)
        val eventLocationView = supportFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment

        eventNameTextView.text = eventName
        eventDateTextView.text = eventDate
        eventTimeTextView.text = eventTime

        eventLocationView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Placer un marqueur à l'emplacement de l'événement
        val markerOptions = MarkerOptions().position(eventLocation).title("Emplacement de l'événement")
        googleMap.addMarker(markerOptions)

        // Déplacez la caméra pour afficher l'emplacement de l'événement
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(eventLocation, 15f)
        googleMap.animateCamera(cameraUpdate)
    }
}