package com.example.agenda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EventDetailsActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        // Assurez-vous d'avoir initialisé le MapView correctement en utilisant la clé API Google Maps
        MapsInitializer.initialize(applicationContext)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap ->
            // Ici, vous pouvez ajouter un marqueur pour la localisation de l'événement
            // Par exemple :
            val eventLocation = LatLng(latitude, longitude)
            googleMap.addMarker(MarkerOptions().position(eventLocation).title("Localisation de l'événement"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocation, 15f))
        }
    }

    // Autres méthodes nécessaires pour la gestion du cycle de vie du MapView
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
