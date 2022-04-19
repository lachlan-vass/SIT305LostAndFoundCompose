package com.lachlanvass.sit305lostandfoundcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lachlanvass.sit305lostandfoundcompose.ui.theme.SIT305LostAndFoundComposeTheme

class ListActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = (1..50).toList()

        val db = AppDatabase.getDatabase(this)
        val posts = db.postDao().getAll()


        setContent {

            val context = LocalContext.current

            Column() {

                posts.forEach {
                        post -> Row() {

                    Card(
                        shape = MaterialTheme.shapes.medium,
                        backgroundColor = MaterialTheme.colors.surface,
                        contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.surface),
                        elevation = 10.dp,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        onClick = {

                            val intent = Intent(context, SinglePost::class.java)
                            intent.putExtra("Name", post.name)
                            intent.putExtra("Date", post.date)
                            intent.putExtra("Description", post.description)
                            intent.putExtra("Location", post.location)
                            context.startActivity(intent)
                        }
                    ) {
                        Text(text = "${post.name} Posted on: ${post.date}" , modifier = Modifier.padding(10.dp))
                    }

                }
                }
            }
        }
    }

    private fun openPostActivity(context: Context, post: Post) {

        val intent = Intent(context, SinglePost::class.java)
        intent.putExtra("Name", post.name)
        context.startActivity(intent)
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