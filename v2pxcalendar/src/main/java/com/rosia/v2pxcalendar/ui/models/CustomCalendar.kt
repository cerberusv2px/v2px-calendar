package com.rosia.v2pxcalendar.ui.models

data class CustomCalendar(
	val date: String,
	val day: String,
	val month: Int,
	val fullDate: String,
	var hasEvents: Boolean = false,
	var startDate: String? = null,
	var endDate: String? = null,
	var status: String? = null,
	var isMiddleDate: Boolean = false,
	var isStartDate: Boolean = false,
	var isEndDate: Boolean = false
)