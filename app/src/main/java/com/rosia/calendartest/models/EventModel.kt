package com.rosia.calendartest.models

data class EventModel(
	val startDate: String,
	val endDate: String? = null,
	val status: String
)