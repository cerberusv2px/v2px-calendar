package com.rosia.v2pxcalendar.ui.models

data class EventModel(
	val startDate: String,
	val endDate: String? = null,
	val status: String
)