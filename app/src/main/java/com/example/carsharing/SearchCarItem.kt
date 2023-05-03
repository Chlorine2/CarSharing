package com.example.carsharing

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carsharing.models.CarItems

@Composable
fun carItem(data : CarItems){

Card(elevation = 4.dp, shape = RoundedCornerShape(100),
    modifier = Modifier.fillMaxWidth().requiredHeight(120.dp).padding(8.dp)) {
    Row{

        Image(painter = painterResource(id = data.picture), contentDescription = null,
            contentScale = ContentScale.Crop, modifier = Modifier.width(110.dp)
                .height(110.dp).padding(start = 10.dp,top= 3.dp, bottom = 3.dp)
                .clip(RoundedCornerShape(120.dp)))

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = "Car", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold
            )
            Text(text = "Mark sdfsdf sdfsdf")

        }


    }

}

}