package com.example.carsharing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsharing.models.DataSource
import com.example.carsharing.viewModels.SharedViewModel

@Composable
fun RentedCarScreen(viewModel: SharedViewModel){

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {

        Text(text = "Cars you rent",
            style = MaterialTheme.typography.h3,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        LazyColumn {
            items(DataSource().dataCars()) { data ->
                carItem(data = data, viewModel, OnListButton = {})

            }
        }
        if (DataSource().dataCars().isEmpty()) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()){

                Text(text = "You have not ranted car yet")
                Button( onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription ="Cart button icon",
                        modifier = Modifier.size(20.dp))

                    Text(text = "Add to cart",Modifier.padding(start = 10.dp))
                }
                }
            }
        }

}
