package com.lachlanvass.sit305lostandfoundcompose

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.util.Log.ERROR
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.*
import java.util.*

fun getLatLngFromCityName(cityName: String?): LatLng {
    return when(cityName) {

        "Sydney" -> LatLng(-34.0, -151.0)
        null -> LatLng(0.0,0.0)
        else -> LatLng(0.0,0.0)
    }
}

class LostAndFoundMapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)
        val posts = db.postDao().getAll()

        val addresses = posts.map { getLatLngFromCityName(it.location) }

        setContent {

            val mapView = rememberMapViewWithLifecycle()
            val context = LocalContext.current


            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                AndroidView({ mapView }) {mapView->
                    CoroutineScope(Dispatchers.Main).launch {
                        mapView.getMapAsync { map ->

                            map.uiSettings.isZoomControlsEnabled = true

                            val markerCoords = addresses.map {
                                it -> LatLng(it.latitude, it.longitude)
                            }

                            markerCoords.forEach { it ->
                                val marker = MarkerOptions()
                                    .position(it)

                                map.addMarker(marker)
                            }

                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(markerCoords.first(),6f))
                        }


                    }

                }
            }


        }
    }
}