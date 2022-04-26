package com.lachlanvass.sit305lostandfoundcompose

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lachlanvass.sit305lostandfoundcompose.ui.theme.SIT305LostAndFoundComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            SIT305LostAndFoundComposeTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = {
                    val intent = Intent(context, CreateActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = "Create Lost and Found Post")
                }
                Button(onClick = {
                    val intent = Intent(context, ListActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = "View Lost and Found Posts")
                }

                Button(onClick = {
                    val intent = Intent(context, LostAndFoundMapActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = "Show on Map")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SIT305LostAndFoundComposeTheme {
        Greeting("Android")
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Create Lost and Found Post")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "View Lost and Found Posts")
        }
    }
}