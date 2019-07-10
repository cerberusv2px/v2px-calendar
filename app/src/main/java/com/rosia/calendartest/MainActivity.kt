package com.rosia.calendartest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rosia.calendartest.databinding.ActivityMainBinding
import com.rosia.v2pxcalendar.builder.CalendarV2pxBuilder
import com.rosia.v2pxcalendar.models.EventModel
import com.rosia.v2pxcalendar.ui.adapter.CalendarDateAdapter

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

		/*val calendarV2pxBuilder = CalendarV2pxBuilder(this@MainActivity)
				.setHeaderColor(R.color.black_2e384d)
				.setEvents(getEventDate())
				.build()
		calendarV2pxBuilder.show()*/

		binding.calendarView.setEvents(getEventDate()).show()
	}

	private fun getEventDate(): List<EventModel> {
		return listOf(
			EventModel(
				"2019-07-01",
				"2019-07-01",
				CalendarDateAdapter.STATUS_ACCEPTED
			),
			EventModel(
				"2019-07-02",
				"2019-07-02",
				CalendarDateAdapter.STATUS_REJECTED
			),
			EventModel(
				"2019-07-03",
				"2019-07-04",
				CalendarDateAdapter.STATUS_ACCEPTED
			),
			EventModel(
				"2019-07-08",
				"2019-07-13",
				CalendarDateAdapter.STATUS_PENDING
			)
		)
	}
}
