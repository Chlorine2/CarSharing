package com.example.carsharing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.carsharing.models.Cars
import com.example.carsharing.ui.theme.CarSharingTheme
import com.example.carsharing.viewModels.SharedViewModel

@Composable
fun CarsContent( viewModel: SharedViewModel, OnListButton :() -> Unit){

    val text by viewModel.searchCar.collectAsState()

    val selectedIndex by viewModel.searchCity.collectAsState()

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Enter parameters of cars:",
            style = MaterialTheme.typography.h3,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        OutlinedTextField(value = text,
            onValueChange = {viewModel.onSearchCarChange(it)},
            label = { Text(text = "Search Cars") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),

            shape = RoundedCornerShape(100)

        )

        LargeDropdownMenu(
            label = "Choose City",
            items = listOf("Lviv"),
            selectedIndex = selectedIndex,
            onItemSelected = { index, _ -> viewModel.onSearchCityChange(index) },
        )


        Text(text = "Cars:",
            style = MaterialTheme.typography.h3,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 5.dp)
                .align(alignment = Alignment.Start)
        )
        LazyColumn{
            items(viewModel.availableCars.value) { data ->

                CarItem(data = data, viewModel, OnListButton = OnListButton, false)
            }
        }
    }

}







@Composable
fun <T> LargeDropdownMenu(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    notSetLabel: String? = null,
    items: List<T>,
    selectedIndex: Int = -1,
    onItemSelected: (index: Int, item: T) -> Unit,
    selectedItemToString: (T) -> String = { it.toString() },
    drawItem: @Composable (T, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        LargeDropdownMenuItem(
            text = item.toString(),
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .height(IntrinsicSize.Min)
        .padding(top = 10.dp)) {

        OutlinedTextField(
            value = items.getOrNull(selectedIndex)?.let { selectedItemToString(it) } ?: "Choose city",
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            trailingIcon = {
                val icon = if (expanded)
                    painterResource(R.drawable.ic_baseline_arrow_drop_up_24)
                else
                    painterResource(R.drawable.ic_baseline_arrow_drop_down_24)

                Icon(painter = icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            onValueChange = { },
            readOnly = true,
            shape = RoundedCornerShape(100),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.primary, textColor = Color.White)


        )

        // Transparent clickable surface on top of OutlinedTextField
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
                .clip(MaterialTheme.shapes.small)
                .clickable(enabled = enabled) { expanded = true },
            color = Color.Transparent,
        ) {}
    }

    if (expanded) {
        Dialog(
            onDismissRequest = { expanded = false },
        ) {
            CarSharingTheme {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                ) {
                    val listState = rememberLazyListState()
                    if (selectedIndex > -1) {
                        LaunchedEffect("ScrollToSelected") {
                            listState.scrollToItem(index = selectedIndex)
                        }
                    }

                    LazyColumn(modifier = Modifier.fillMaxWidth(), state = listState) {
                        if (notSetLabel != null) {
                            item {
                                LargeDropdownMenuItem(
                                    text = notSetLabel,
                                    selected = false,
                                    enabled = false,
                                    onClick = { },
                                )
                            }
                        }
                        itemsIndexed(items) { index, item ->
                            val selectedItem = index == selectedIndex
                            drawItem(
                                item,
                                selectedItem,
                                true
                            ) {
                                onItemSelected(index, item)
                                expanded = false
                            }

                            if (index < items.lastIndex) {
                                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LargeDropdownMenuItem(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = when {
        !enabled -> MaterialTheme.colors.onSurface.copy(alpha = 0f)
        selected -> MaterialTheme.colors.primary.copy(alpha = 1f)
        else -> MaterialTheme.colors.onSurface.copy(alpha = 1f)
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(modifier = Modifier
            .clickable(enabled) { onClick() }
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}