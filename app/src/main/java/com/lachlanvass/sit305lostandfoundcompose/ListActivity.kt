package com.lachlanvass.sit305lostandfoundcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lachlanvass.sit305lostandfoundcompose.ui.theme.SIT305LostAndFoundComposeTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = (1..50).toList()

        val db = AppDatabase.getDatabase(this)

        val examplePost = Post(
            1,
            "hello",
            "1234ABC",
            "I lost my cat",
            "1/21/11",
            "Brisbane"
        )

        val examplePost2 = Post(
            2,
            "hello",
            "1234ABC",
            "I lost my cat",
            "1/21/11",
            "Brisbane"
        )

        db.postDao().insert(examplePost)
        db.postDao().insert(examplePost2)

        val posts = db.postDao().getAll()


        setContent {

            Column() {

                posts.forEach {
                        post -> Row() {

                    Text(text = post.toString())

                }
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SIT305LostAndFoundComposeTheme {
        Greeting2("Android")
    }
}