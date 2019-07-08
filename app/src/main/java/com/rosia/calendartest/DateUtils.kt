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

fun getBetweenDates(startDate: String, endDate: String): List<String> {
	val df = SimpleDateFormat("yyyy-MM-dd", Locale.US)
	val startCalendar = Calendar.getInstance()
	startCalendar.time = df.parse(startDate)

	val endCalendar = Calendar.getInstance()
	endCalendar.time = df.parse(endDate)

	val betweenDates = mutableListOf<String>()
	while (startCalendar.before(endCalendar)) {
		val result = startCalendar.time
		startCalendar.add(Calendar.DATE, 1)
		betweenDates.add(df.format(result))
	}
	betweenDates.add(endDate)

	//betweenDates.removeAt(0)
	//	betweenDates.removeAt(betweenDates.size - 1)
	// println(betweenDates)
	return betweenDates
}
