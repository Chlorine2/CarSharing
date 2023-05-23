package com.example.carsharing

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.Search


import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carsharing.models.DataSource
import com.example.carsharing.viewModels.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyCarsScreen(viewModel: SharedViewModel, OnAddCarButton :() -> Unit){


    Scaffold(floatingActionButton = {FloatingActionButtons(OnAddCarButton)} ) {
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
                items(DataSource().dataCars()) { data ->
                    CarItem(data = data, viewModel, OnListButton = {}, false)

                }
            }
            if (DataSource().dataCars().isEmpty()) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()){

                    Text(text = "Please add your car")

                }

            }

        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun FloatingActionButtons(OnAddCarButton :() -> Unit) {

    val ctx = LocalContext.current

        // on below line we are creating a text

        ExtendedFloatingActionButton(
            // on below line we are setting text for our fab
            text = { Text(text = "Add car") },
            // on below line we are adding click listener.
            onClick = {
                OnAddCarButton()
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
