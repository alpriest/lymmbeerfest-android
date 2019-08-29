package com.alpriest.lymmbeerfest.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.alpriest.lymmbeerfest.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import java.util.Locale

class HomeFragment : Fragment() {
    private var mapView: MapView? = null
    private val villageHall = LatLng(53.381098, -2.476424)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.home, container, false)

        this.mapView = fragment.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        MapsInitializer.initialize(context!!)

        mapView?.getMapAsync { googleMap ->
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(villageHall, 17f))

            val marker = MarkerOptions().position(villageHall).title("Lymm Village Hall")
            googleMap.addMarker(marker)
        }

        fragment.findViewById<View>(R.id.directions).setOnClickListener {
            val url = String.format(Locale.ENGLISH, "https://www.google.com/maps/dir/?api=1&destination=%f,%f", villageHall.latitude, villageHall.longitude)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        return fragment
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}
