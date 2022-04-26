package com.lachlanvass.sit305lostandfoundcompose

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.widget.DatePicker
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lachlanvass.sit305lostandfoundcompose.ui.theme.SIT305LostAndFoundComposeTheme
import kotlinx.coroutines.selects.select
import kotlin.random.Random

class CreateActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val db = AppDatabase.getDatabase(this)

        setContent {

            val context = LocalContext.current

            var name by remember { mutableStateOf("") }
            var phone by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var date by remember { mutableStateOf("") }
            var location by remember { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }
            val cityOptions = listOf("Sydney", "Brisbane", "Melbourne", "Canberra")
            var selectedCity by remember {mutableStateOf(cityOptions[0])}


            Column {

                var selectedButton by remember {
                    mutableStateOf("Lost")
                }

                Row {

                    val options = setOf("Lost", "Found")

                    Text(text = "Post Type: ")
                    options.forEach { option ->
                        RadioButton(
                            selected = selectedButton == option,
                            onClick = { selectedButton = option })
                        Text(
                            text = option, modifier = Modifier
                                .clickable(onClick = { selectedButton = option })
                                .padding(4.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                    }

                }
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    label = { Text(text = "Name") }
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    singleLine = true,
                    label = { Text(text = "Phone Number") }


                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    singleLine = true,
                    label = { Text(text = "Description") }

                )

                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    singleLine = true,
                    label = { Text(text = "Date") }

                )

//                OutlinedTextField(
//                    value = location,
//                    onValueChange = {location = it},
//                    singleLine = true,
//                    label = { Text(text = "Location")},
//
//                )



                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                    expanded = !expanded
                }) {

                    TextField(
                        readOnly = true,
                        value = selectedCity,
                        onValueChange = { },
                        label = { Text("City") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )

                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

                        cityOptions.forEach { city ->

                            DropdownMenuItem(onClick = {
                                selectedCity = city
                                expanded = false
                            }) {

                                Text(text = selectedCity)
                            }

                        }
                    }
                }


                Button(onClick = {

                    val post = Post(
                        Random.nextInt(),
                        name,
                        phone,
                        description,
                        date,
                        location,
                        selectedButton
                    )

                    db.postDao().insert(post)

                    Toast.makeText(context, "Post Saved", Toast.LENGTH_SHORT).show()

                    val intent = Intent(context, ListActivity::class.java)
                    context.startActivity(intent)
                }) {

                    Text(text = "Save Post")
                }

            }
        }

    }

}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}


@Composable
fun InputAndLabel(fieldName: String) {
    var name by remember {
        mutableStateOf<String>("")
    }

    Text(text = fieldName)
    return OutlinedTextField(value = name, onValueChange = { name = it }, singleLine = true)
}

//@Composable
//fun AustralianCityDropdown() {
//    DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
//
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {

    Column {
        InputAndLabel("Name")
        InputAndLabel("Phone")
        InputAndLabel("Description")
        InputAndLabel("Date")
        InputAndLabel("Location")
    }
}
