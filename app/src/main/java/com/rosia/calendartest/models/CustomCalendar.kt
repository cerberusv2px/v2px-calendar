package com.rosia.calendartest.models

data class CustomCalendar(
	val date: String,
	val day: String,
	val hasEvents: Boolean = false,
	val startDate: String? = null,
	val endDate: String? = null,
	val status: String? = null
) {
}