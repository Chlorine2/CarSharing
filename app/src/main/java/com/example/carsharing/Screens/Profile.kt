import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.carsharing.ListOfScreens
import com.example.carsharing.MyCarsScreen
import com.example.carsharing.R
import com.example.carsharing.RentedCarScreen
import com.example.carsharing.navigateSingleTopTo
import com.example.carsharing.viewModels.SharedViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabbedApp(navController: NavHostController, viewModel: SharedViewModel) {
    val selectedTabIndex = remember { mutableStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            backgroundColor = Color.White, // Білий фон
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value]),
                    color = Color.Black // Чорний колір підкреслення
                )
            },
            modifier = Modifier.height(58.dp) // Set the height of the TabRow here

        ) {
            Tab(
                selected = selectedTabIndex.value == 0,
                onClick = { selectedTabIndex.value = 0 },
                modifier = Modifier.padding(horizontal = 16.dp) // Додаткові відступи
            ) {
                Text("Profile", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold) // Чорний колір тексту
            }
            Tab(
                selected = selectedTabIndex.value == 1,
                onClick = { selectedTabIndex.value = 1 },
                modifier = Modifier.padding(horizontal = 16.dp) // Додаткові відступи
            ) {
                Text("Rented", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold) // Чорний колір тексту
            }
            Tab(
                selected = selectedTabIndex.value == 2,
                onClick = { selectedTabIndex.value = 2 },
                modifier = Modifier.padding(horizontal = 16.dp) // Додаткові відступи
            ) {
                Text("My Cars", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold) // Чорний колір тексту
            }
        }


            when (selectedTabIndex.value) {
                0 -> AllProfiles()
                1 -> RentedCarScreen(viewModel)
                2 -> MyCarsScreen(viewModel, OnAddCarButton = {navController.navigateSingleTopTo(ListOfScreens.AddCar.name)})
            }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PreviewTabbedApp(viewModel: SharedViewModel, navController : NavHostController) {
    TabbedApp(navController, viewModel = viewModel)
}



@Composable
fun AllProfiles(){
    ProfileZone()
    ConfirmProfile()
    AboutSection()
}
@Composable
fun ProfileZone() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 240.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Верхня частина з ім'ям користувача та фото
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "User",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Some description lalalallalalalal dsal; kldkaslkdd laksdl kakd dasdkjasdkj ",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                    color = Color.LightGray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Фото профілю",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable {
                        // Обробка натискання на фото профілю
                        // Виконати дії зміни фото
                    },
                contentScale = ContentScale.FillBounds
            )
        }




        // Роздільник
        Spacer(modifier = Modifier.height(22.dp))

        // Надпис "Змінити фото профілю"
        Text(
            text = "Змінити фото профілю",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable {
                    // Обробка натискання на надпис "Змінити фото профілю"
                    // Виконати дії зміни фото
                }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Надпис "Редагувати інформацію про себе"
        Text(
            text = "Редагувати інформацію про себе",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable {
                    // Обробка натискання на надпис "Редагувати інформацію про себе"
                    // Виконати дії редагування інформації
                }
        )

        Spacer(modifier = Modifier.height(18.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}


@Composable
fun ConfirmProfile(){
    // Заголовок "Підтвердіть свій профіль"
    Text(
        text = "Підтвердіть свій профіль",
        color = Color.Black,
        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(horizontal = 30.dp)
    )

    Spacer(modifier = Modifier.height(18.dp))

// Рядок "Підтвердіть особу" з позначкою "плюс"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Підтвердіть особу",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .clickable {
                    // Обробка натискання на фото профілю
                    // Виконати дії зміни фото
                },
        )
        Text(
            text = "+",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }

    Spacer(modifier = Modifier.height(18.dp))

// Рядок "Підтвердіть email" з позначкою "плюс"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Підтвердіть email",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .clickable {
                    // Обробка натискання на фото профілю
                    // Виконати дії зміни фото
                },
        )
        Text(
            text = "+",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }

    Spacer(modifier = Modifier.height(18.dp))

// Рядок "Підтвердіть номер" з позначкою "галочка"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Підтвердіть номер",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .clickable {
                    // Обробка натискання на фото профілю
                    // Виконати дії зміни фото
                },
        )
        Text(
            text = "✓",
            color = Color.Green,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }

    Spacer(modifier = Modifier.height(25.dp))


    Divider(
        color = Color.LightGray,
        thickness = 4.dp
    )

}



@Composable
fun AboutSection() {

    Spacer(modifier = Modifier.height(18.dp))

    // Заголовок "Про себе"
    Text(
        text = "Про себе",
        color = Color.Black,
        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(horizontal = 30.dp)
    )

    Spacer(modifier = Modifier.height(18.dp))

    // Рядок "Додати інформацію про себе" з позначкою "плюс"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Додати інформацію про себе",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .clickable {
                    // Обробка натискання на рядок "Додати інформацію про себе"
                    // Виконати дії додавання інформації
                },
        )
        Text(
            text = "+",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }

    Spacer(modifier = Modifier.height(18.dp))

    // Рядок "Додати вподобання" з позначкою "галочка"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Додати вподобання",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .clickable {
                    // Обробка натискання на рядок "Додати вподобання"
                    // Виконати дії додавання вподобань
                },
        )
        Text(
            text = "✓",
            color = Color.Green,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }

    Spacer(modifier = Modifier.height(18.dp))


    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun TransportSection() {
    Spacer(modifier = Modifier.height(18.dp))
    // Заголовок "Транспортні засоби"
    Text(
        text = "Транспортні засоби",
        color = Color.Black,
        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(horizontal = 30.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Рядок "Додати авто" з позначкою "плюс"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Додати авто",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .clickable {
                    // Обробка натискання на рядок "Додати авто"
                    // Виконати дії додавання авто
                },
        )
        Text(
            text = "+",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }

    Spacer(modifier = Modifier.height(16.dp))



}





