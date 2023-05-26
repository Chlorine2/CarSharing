package com.example.carsharing.Screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carsharing.models.Cars
import com.example.carsharing.viewModels.SharedViewModel


@Composable
fun RequestCarItem(data : Cars, viewModel: SharedViewModel, OnListButton : () -> Unit,
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

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = data.vendor + " " +
                            data.model, style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold)
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


                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = data.vendor + " " + data.model, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold
                    )
                    Text(text = "color : " + data.color)
                    Text(text = "description : " + data.description)


                }


            }

        }

    }


}