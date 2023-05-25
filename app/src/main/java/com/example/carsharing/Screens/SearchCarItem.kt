package com.example.carsharing

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carsharing.models.Car
import com.example.carsharing.models.Cars
import com.example.carsharing.viewModels.SharedViewModel


@Composable
fun CarItem(data : Cars, viewModel: SharedViewModel, OnListButton : () -> Unit,
            ItemHorizontal : Boolean){

    if(!ItemHorizontal){
        Card(elevation = 4.dp, shape = RoundedCornerShape(10),
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(450.dp)
                .padding(8.dp)
                .clickable {

                    viewModel.updateListCar(data)
                    OnListButton()

                }) {
            Column{

                Image(painter = painterResource(id = R.drawable.image1), contentDescription = null,
                    contentScale = ContentScale.Crop, modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 3.dp, bottom = 3.dp)
                        .clip(RoundedCornerShape(20.dp)))

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = data.vendor + " " +
                            data.model, style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold)

                    Text(text = "$" + data.price + " / hour")
                    Text(text = "color : " + data.color)
                    Text(text = "description : " + data.description)


                }


            }

        }
    }
    else{
        Card(elevation = 4.dp, shape = RoundedCornerShape(70),
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(160.dp)
                .padding(8.dp)
                .clickable {

                    viewModel.updateListCar(data)
                    OnListButton()

                }) {
            Row(){

                Image(painter = painterResource(id = R.drawable.image1), contentDescription = null,
                    contentScale = ContentScale.Crop, modifier = Modifier
                        .fillMaxHeight()
                        .width(150.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(100.dp)))

                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = data.vendor + " " + data.model, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold
                    )
                    Text(text = "$" + data.price + " / hour")
                    Text(text = "color : " + data.color)
                    Text(text = "description : " + data.description)


                }


            }

        }

    }


}