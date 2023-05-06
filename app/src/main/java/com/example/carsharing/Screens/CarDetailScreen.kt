package com.example.carsharing.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carsharing.R
import com.example.carsharing.models.Car
import com.example.carsharing.viewModels.SharedViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailedScreen(viewModel: SharedViewModel){
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
//
    val car = viewModel.car
//
    Log.d("teg", car.value.picture.toString())



        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
            Text(text = "Detailed information :", style = MaterialTheme.typography.h6,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp),
                fontSize = 25.sp)

                Image(
                    painter = painterResource(id = car.value.picture), contentDescription = "1",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(400.dp)
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Column(horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxWidth()
                )
                {

                Text(
                    text = car.value.vendor + " " + car.value.model, style = MaterialTheme.typography.h6,
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