package com.example.carsharing

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import com.example.carsharing.models.CarItems
import com.example.carsharing.models.DataSource
import com.example.carsharing.ui.theme.CarSharingTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarSharingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    ScaffoldSimple()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarSharingTheme {
        ScaffoldSimple()
    }
}

@Composable
fun ScaffoldSimple(){

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {},
            Modifier
                .padding(bottom = 20.dp, end = 10.dp)
                .scale(1.1f), backgroundColor = MaterialTheme.colors.surface)
        {
            Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
        } },
        bottomBar = { BottomBar() },
        content = { CarsContent() },
    )
}

@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 1000.dp, backgroundColor = MaterialTheme.colors.surface) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Search,"")
        },
            label = { Text(text = "Cars") },
            selected = (selectedIndex.value == 0),
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = Color.Gray,

            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
            label = { Text(text = "Ordered") },
            selected = (selectedIndex.value == 1),
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = Color.Gray,

            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
            label = { Text(text = "My Cars") },
            selected = (selectedIndex.value == 2),
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = Color.Gray,
            onClick = {
                selectedIndex.value = 2
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 2),
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = Color.Gray,
            onClick = {
                selectedIndex.value = 2
            })
    }
}

@Composable
fun CarsContent(){

    val text = remember{mutableStateOf("")}
    var indexx : Int = 1;
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Enter parameters of cars:",
            style = MaterialTheme.typography.h3,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        OutlinedTextField(value = text.value,
            onValueChange = {text.value = it},
            label = { Text(text = "Search Cars")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),

            shape = RoundedCornerShape(100)

            )

        var selectedIndex by remember { mutableStateOf(-1) }
        LargeDropdownMenu(
            label = "Choose City",
            items = listOf("Lviv"),
            selectedIndex = selectedIndex,
            onItemSelected = { index, _ -> selectedIndex = index },
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
            items(DataSource().dataCars()) { data ->
                carItem(data = data)
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
                backgroundColor =MaterialTheme.colors.primary, textColor = Color.White)


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