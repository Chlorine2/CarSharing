package com.example.carsharing.Screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carsharing.ListOfScreens
import com.example.carsharing.R
import com.example.carsharing.models.Car
import com.example.carsharing.viewModels.SharedViewModel

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailedScreen(viewModel: SharedViewModel, OnRentCarButton :() -> Unit) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
//
    val car = viewModel.car
//
    Log.d("teg", car.value.picture.toString())
    val pairList = listOf(Pair("Location:", car.value.location), Pair("color:", car.value.color),
        Pair("description:", car.value.description))


    Scaffold(floatingActionButton = { FloatingActionButtons(OnRentCarButton) }) {
            padding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
        ) {

            Image(
                painter = painterResource(id = car.value.picture), contentDescription = "1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(350.dp)
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxWidth()
            )
            {

                Text(
                    text = car.value.vendor + " " + car.value.model,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp),
                    fontSize = 25.sp
                )

                Text(
                    text = "$${car.value.price} / hour", style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp),
                    fontSize = 28.sp, color = Color(0xFF25951B)
                )
                SText(text = "location:")
                BText(text = "Lviv")
                SText(text = "color:")
                BText(text = car.value.color)
                SText(text = "description:")
                BText(text = car.value.description)
            }
        }

    }
}


@Composable
fun BText(text : String){
    Text(text = text, style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(start = 10.dp),
        fontSize = 22.sp)
}


@Composable
fun SText(text : String){
    Text(text = text, style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(start = 10.dp),
        fontSize = 20.sp, fontWeight = FontWeight.Light
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun FloatingActionButtons(OnRentCarButton :() -> Unit) {

    val ctx = LocalContext.current

    // on below line we are creating a text

    ExtendedFloatingActionButton(
        // on below line we are setting text for our fab
        text = { Text(text = "Rent a car") },
        // on below line we are adding click listener.
        onClick = {
            OnRentCarButton()
        },
        // on below line adding
        // a background color.
        backgroundColor = MaterialTheme.colors.primary,
        // on below line we are
        // adding a content color.
        contentColor = Color.White,
        // on below line we are
        // adding icon for our fab
        icon = { Icon(Icons.Filled.Add, "") }
    )

}