package com.lachlanvass.sit305lostandfoundcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lachlanvass.sit305lostandfoundcompose.ui.theme.SIT305LostAndFoundComposeTheme

class SinglePost : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uid = intent.getIntExtra("UID", 0)
        val name = intent.getStringExtra("Name") ?: "Name Unknown"
        val phone = intent.getStringExtra("Phone") ?: "Phone unknown"
        val date = intent.getStringExtra("Date") ?: "Date Unknown"
        val description = intent.getStringExtra("Description") ?: "description..."
        val location = intent.getStringExtra("Location") ?: "Location Unknown"
        val type = intent.getStringExtra("Type") ?: "Lost"

        val post = Post(
            uid,
            name,
            phone,
            description,
            date,
            location,
            type
        )

        val db = AppDatabase.getDatabase(this)
        setContent {
            val context = LocalContext.current

            val defaultTextPadding = 10.dp
            Column {

                Text(text = "Lost and Found Post", Modifier.padding(defaultTextPadding))
                Text(text = "Name: $name", Modifier.padding(defaultTextPadding))
                Text(text = "Date: $date", Modifier.padding(defaultTextPadding))
                Text(text = "Description: $description", Modifier.padding(defaultTextPadding))
                Text(text = "Location: $location", Modifier.padding(defaultTextPadding))
                Text(text = "Post ID: ${post.uid}", Modifier.padding(defaultTextPadding))

                Button(onClick = {
                    db.postDao().deleteById(post.uid)

                    val intent = Intent(context, ListActivity::class.java)
                    startActivity(intent)

                }) {

                    Text(text = "Remove")
                }
            }

        }
    }
}