package com.example.carsharing.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsharing.CarItem
import com.example.carsharing.models.DataSource
import com.example.carsharing.viewModels.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VolunteerRequestCarScreen(viewModel: SharedViewModel, OnAddRequestButton :() -> Unit){


    Scaffold(floatingActionButton = { FloatAddButton(OnAddRequestButton) } ) {
            padding ->

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {

            Text(text = "Cars you own",
                style = MaterialTheme.typography.h3,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            LazyColumn {
                viewModel.getOwnedCars()

                items(viewModel.requestCar.value) { data ->
                    CarItem(data = data, viewModel, OnListButton = {}, false)
                }
            }
            if (DataSource().dataCars().isEmpty()) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()){

                    Text(text = "Please add your request")

                }

            }

        }
    }
}



@SuppressLint("SuspiciousIndentation")
@Composable
fun FloatAddButton(OnAddRequestButton :() -> Unit) {

    val ctx = LocalContext.current

    // on below line we are creating a text

    ExtendedFloatingActionButton(
        // on below line we are setting text for our fab
        text = { Text(text = "Add request") },
        // on below line we are adding click listener.
        onClick = {
            OnAddRequestButton()
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