import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.view.ContextThemeWrapper
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsharing.CarItem
import com.example.carsharing.R
import com.example.carsharing.viewModels.SharedViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RentedCarDetailScreen(viewModel: SharedViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        CarItem(viewModel.car.value, viewModel, OnListButton = {}, true)
        BookingScreen()
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PeriodPicker(onPeriodSelected: (startDate: LocalDateTime, endDate: LocalDateTime) -> Unit) {
    val context = LocalContext.current
    var startDate by remember { mutableStateOf<LocalDateTime?>(null) }
    var endDate by remember { mutableStateOf<LocalDateTime?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "Start Date and Time",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                showDateTimePicker(context) { dateTime ->
                    startDate = dateTime
                }
            },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                if (startDate != null) startDate!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                else "Select Start Date and Time",
                style = MaterialTheme.typography.button.copy(fontSize = 18.sp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "End Date and Time",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                showDateTimePicker(context) { dateTime ->
                    endDate = dateTime
                }
            },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                if (endDate != null) endDate!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                else "Select End Date and Time",
                style = MaterialTheme.typography.button.copy(fontSize = 18.sp)
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Button(
            onClick = {
                if (startDate != null && endDate != null) {
                    onPeriodSelected(startDate!!, endDate!!)
                }
            },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                "Book Car",
                style = MaterialTheme.typography.button.copy(fontSize = 18.sp)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun showDateTimePicker(context: Context, onDateTimeSelected: (LocalDateTime) -> Unit) {
    val currentDate = LocalDate.now()
    val currentDateTime = LocalDateTime.now()

    val datePickerDialog = DatePickerDialog(
        ContextThemeWrapper(context, R.style.CalendarStyle), // Apply the custom style
        { _, year, month, dayOfMonth ->
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            val timePickerDialog = TimePickerDialog(
                context,
                R.style.CalendarStyle, // Встановіть стиль, створений у кроці 1
                { _, hourOfDay, minute ->
                    val selectedTime = LocalTime.of(hourOfDay, minute)
                    onDateTimeSelected(LocalDateTime.of(selectedDate, selectedTime))
                },
                currentDateTime.hour,
                currentDateTime.minute,
                android.text.format.DateFormat.is24HourFormat(context)
            )
            timePickerDialog.show()
        },
        currentDate.year,
        currentDate.monthValue - 1,
        currentDate.dayOfMonth
    )
    datePickerDialog.datePicker.minDate = currentDate.toEpochDay() * 86400000L
    datePickerDialog.datePicker.maxDate =
        currentDate.plusYears(1).toEpochDay() * 86400000L
    datePickerDialog.show()
}





@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingScreen() {
    var bookingInfo by remember { mutableStateOf<String?>(null) }

    Column {
        PeriodPicker { startDate, endDate ->
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            bookingInfo = "You have booked the car from ${startDate.format(formatter)} to ${endDate.format(formatter)}"
        }
        Spacer(modifier = Modifier.height(26.dp))
        if (bookingInfo != null) {
            Text(
                bookingInfo!!,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}







