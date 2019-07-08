package com.rosia.calendartest.models

data class CustomCalendar(
	val date: String,
	val day: String,
	var hasEvents: Boolean = false,
	var startDate: String? = null,
	var endDate: String? = null,
	var status: String? = null
)