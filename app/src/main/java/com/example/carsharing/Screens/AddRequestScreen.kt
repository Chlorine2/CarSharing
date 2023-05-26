package com.example.carsharing.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.carsharing.R
import com.example.carsharing.ui.theme.CarSharingTheme
import com.example.carsharing.viewModels.SharedViewModel

@Composable
fun AddRequestScreen(viewModel: SharedViewModel) {
    val pairList = listOf(
        Triple("model:", "set model", 0),
        Triple("color:", "set color", 1),
        Triple("description:", "set description", 2)
    )

    var selectedIndex1 by remember { mutableStateOf(-1) }
    var selectedIndex2 by remember { mutableStateOf(-1) }
    var selectedIndex3 by remember { mutableStateOf(-1) }
    var selectedIndex4 by remember { mutableStateOf(-1) }

    var isDeliveryChecked by remember { mutableStateOf(false) }


    val stringList = (1920..2023).map { it.toString() }

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        Text(
            text = "Enter parameters of cars:",
            style = MaterialTheme.typography.h3,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        LargeDropdownMenuRequest(
            label = "Choose vendor",
            items = listOf("BMW", "Mercedes", "Toyota", "Lexus", "Skoda", "Volkswagen"),
            selectedIndex = selectedIndex1,
            onItemSelected = { index, _ -> selectedIndex1 = index },
        )

        LargeDropdownMenuRequest(
            label = "Choose year",
            items = stringList.reversed(),
            selectedIndex = selectedIndex2,
            onItemSelected = { index, _ -> selectedIndex2 = index },
        )

        LargeDropdownMenuRequest(
            label = "Choose Location",
            items = listOf("Lviv"),
            selectedIndex = selectedIndex3,
            onItemSelected = { index, _ -> selectedIndex3 = index },
        )

        LargeDropdownMenuRequest(
            label = "Choose body type",
            items = listOf("Sedan", "SUV", "Hatchback", "Coupe", "MUV", "Van", "Pickup truck", "Crossover", "Station wagon", "Truck"),
            onItemSelected = {index, _ -> selectedIndex4 = index}
        )

        LazyColumn {
            items(pairList) { item ->
                val currentText = viewModel.setProperties[item.third].collectAsState()

                Text(
                    text = item.first,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )

                OutlinedTextField(
                    value = currentText.value,
                    onValueChange = { viewModel.onSetPropertyChange(item.third, it) },
                    label = { Text(text = item.second) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(100),
                    singleLine = true
                )
            }
        }


        Row(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = "Do you need delivery?",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Checkbox(
                checked = isDeliveryChecked,
                onCheckedChange = { isChecked -> isDeliveryChecked = isChecked },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
            )
        }
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(100.dp)
        ) {
            Text(text = "Add Car")
        }
    }
}


@Composable
fun <T> LargeDropdownMenuRequest(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    notSetLabel: String? = null,
    items: List<T>,
    selectedIndex: Int = -1,
    onItemSelected: (index: Int, item: T) -> Unit,
    selectedItemToString: (T) -> String = { it.toString() },
    drawItem: @Composable (T, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        LargeDropdownMenuItemRequest(
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
            value = items.getOrNull(selectedIndex)?.let { selectedItemToString(it) } ?: label,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            trailingIcon = {
                val icon = if (expanded)
                    painterResource( R.drawable.ic_baseline_arrow_drop_down_24)
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
fun LargeDropdownMenuItemRequest(
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

    CompositionLocalProvider(LocalContentColor provides  contentColor) {
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