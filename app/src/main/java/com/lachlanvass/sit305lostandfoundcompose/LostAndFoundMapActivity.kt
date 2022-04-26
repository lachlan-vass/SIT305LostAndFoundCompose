package com.lachlanvass.sit305lostandfoundcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LostAndFoundMapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val mapView = rememberMapViewWithLifecycle()

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

                            val pickUp =  LatLng(-35.016, 143.321)
                            val destination = LatLng(-32.491, 147.309)

                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination,6f))

                            val markerOptions = MarkerOptions()
                                .title("Sydney Opera House")
                                .position(pickUp)
                            map.addMarker(markerOptions)

                            val markerOptionsDestination = MarkerOptions()
                                .title("Restaurant Hubert")
                                .position(destination)
                            map.addMarker(markerOptionsDestination)

                            map.addPolyline(
                                PolylineOptions().add( pickUp,
                                LatLng(-34.747, 145.592),
                                LatLng(-34.364, 147.891),
                                LatLng(-33.501, 150.217),
                                LatLng(-32.306, 149.248),
                                destination))

                        }


                    }



                }
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
}