package com.lachlanvass.sit305lostandfoundcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lachlanvass.sit305lostandfoundcompose.ui.theme.SIT305LostAndFoundComposeTheme

class SinglePost : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("Name") ?: "Name Unknown"
        val date = intent.getStringExtra("Date") ?: "Date Unknown"
        val description = intent.getStringExtra("Description") ?: "description..."
        val location = intent.getStringExtra("Location") ?: "Location Unknown"
        setContent {
            Column {

                Text(text = "Lost and Found Post")
                Text(text = "Name: $name")
                Text(text = "Date: $date")
                Text(text = "Description: $description")
                Text(text = "Location: $location")
            }

        }
    }
}