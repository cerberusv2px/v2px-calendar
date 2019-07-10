package com.rosia.v2pxcalendar.builder

import android.content.Context
import com.rosia.v2pxcalendar.models.EventModel
import com.rosia.v2pxcalendar.properties.CalendarProperties
import com.rosia.v2pxcalendar.ui.CalendarView

class CalendarV2pxBuilder(private val context: Context) {

	private val calendarProps = CalendarProperties()

	fun build(): CalendarView {
		return CalendarView(context, calendarProps)
	}

	fun setHeaderColor(headerColor: Int): CalendarV2pxBuilder {
		calendarProps.headerColor = headerColor
		return this
	}

	fun setEvents(eventList: List<EventModel>): CalendarV2pxBuilder {
		calendarProps.eventDates = eventList
		return this
	}
}