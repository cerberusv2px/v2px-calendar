package com.rosia.calendartest

import com.rosia.calendartest.models.CustomDay
import java.text.SimpleDateFormat
import java.util.*

fun getListOfDays(): List<CustomDay> {
	return listOf(
		CustomDay("Sun", "S"),
		CustomDay("Mon", "M"),
		CustomDay("Tue", "T"),
		CustomDay("Wed", "W"),
		CustomDay("Thu", "T"),
		CustomDay("Fri", "F"),
		CustomDay("Sat", "S")
	)
}

fun getCurrentDate(): String {
	val cal = Calendar.getInstance()
	cal.add(Calendar.DATE, 0)
	val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
	return simpleDateFormat.format(cal.time)
}

fun formatDate(
	inputDate: String,
	inputFormat: String = "yyyy-MM-dd",
	outputFormat: String
): String {
	val outputDateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
	val inputDateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
	val date = inputDateFormat.parse(inputDate)
	return outputDateFormat.format(date)
}

fun getFirstDateOfMonthWithCalendar(calendar: Calendar): String {
	calendar.add(Calendar.DATE, 0)
	val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
	return simpleDateFormat.format(calendar.time)
}

fun getCurrentDate(cal: Calendar): String {
	cal.add(Calendar.DATE, 0)
	val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
	return simpleDateFormat.format(cal.time)
}
