package com.rosia.calendartest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.rosia.calendartest.adapter.CalendarDateAdapter
import com.rosia.calendartest.adapter.CalendarDayAdapter
import com.rosia.calendartest.databinding.ActivityMainBinding
import com.rosia.calendartest.models.CustomCalendar
import com.rosia.calendartest.models.EventModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding
	private lateinit var calendarDateAdapter: CalendarDateAdapter
	private val mCalendar = Calendar.getInstance()

	companion object {
		private const val MONTH_YEAR_FORMAT = "MMMM yyyy"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.apply {
			btnPrevious.setOnClickListener { handlePreviousMonthButtonClicked() }
			btnNext.setOnClickListener { handleNextMonthButtonClicked() }
		}
		setupDayAdapter()
		setupDateAdapter()
		setUpMonth()
	}

	private fun setupDayAdapter() {
		val dayAdapter = CalendarDayAdapter(getListOfDays())
		binding.recyclerviewDay.adapter = dayAdapter
	}

	private fun setupDateAdapter() {
		val dateMonthList = getAllDateInMonth() as MutableList
		val eventList = getEventData()
		for (customCalendar in dateMonthList) {
			val eventDate = eventList.find { it.startDate == customCalendar.fullDate }
			if (eventDate != null) {
				customCalendar.startDate = eventDate.startDate
				customCalendar.endDate = eventDate.endDate
				customCalendar.hasEvents = true
				customCalendar.status = eventDate.status
			}
		}

		/*val eventDates = dateMonthList.filter { it.hasEvents }
		dateMonthList.forEach { mainEvent ->
			if (mainEvent.endDate != null) {
				if (mainEvent.hasEvents) {

					val betweenDates = getBetweenDates(
						mainEvent.startDate ?: mainEvent.fullDate,
						mainEvent.endDate ?: mainEvent.fullDate
					) as MutableList<String>

					if (betweenDates.size == 2) {
						dateMonthList.forEach { customCalendar ->
							if (customCalendar.fullDate == betweenDates.first()) {
								customCalendar.isStartDate = true
								customCalendar.startDate = mainEvent.startDate
								customCalendar.hasEvents = true
							}
							if (customCalendar.fullDate == betweenDates.last()) {
								customCalendar.endDate = mainEvent.endDate
								customCalendar.isEndDate = true
								customCalendar.hasEvents = true
							}

						}
					} else {
						dateMonthList.forEach { customCalendar ->
							if (customCalendar.fullDate == betweenDates.first()) {
								customCalendar.isStartDate = true
								customCalendar.hasEvents = true
								//vbetweenDates.removeAt(0)
							}
							if (customCalendar.fullDate == betweenDates.last()) {
								customCalendar.isEndDate = true
								customCalendar.hasEvents = true
								//betweenDates.removeAt(betweenDates.size - 1)
							}

						}


						betweenDates.forEachIndexed { index, date ->
							if (index != 0 || index != (betweenDates.size - 1)) {
								val dateModel = dateMonthList.find { it.fullDate == date }
								dateModel?.apply {
									isMiddleDate = true
									hasEvents = true
									endDate = mainEvent.endDate
								}
							}

						}
					}
				}
			}
		}*/

		val eventDates = dateMonthList.filter { it.hasEvents }
		eventDates.forEach { dateModel ->
			if (dateModel.endDate != dateModel.startDate && !dateModel.isMiddleDate) {
				val betweenDates = getBetweenDates(
					dateModel.startDate!!,
					dateModel.endDate!!
				) as MutableList<String>

				if (betweenDates.size == 2) {
					val firstEventDate = dateMonthList.find { it.fullDate == dateModel.startDate }
					firstEventDate?.apply {
						isStartDate = true
						startDate = dateModel.startDate
						endDate = dateModel.endDate
						hasEvents = true
						status = dateModel.status
					}

					val lastEventDate = dateMonthList.find { it.fullDate == dateModel.endDate }
					lastEventDate?.apply {
						isEndDate = true
						startDate = dateModel.startDate
						endDate = dateModel.endDate
						hasEvents = true
						status = dateModel.status
					}
				} else {
					betweenDates.forEachIndexed { index, date ->
						if (index == 0) {
							val firstEventDate = dateMonthList.find { it.fullDate == date }
							firstEventDate?.apply {
								isStartDate = true
								startDate = dateModel.startDate
								endDate = dateModel.endDate
								hasEvents = true
								status = dateModel.status
							}
							//betweenDates.removeAt(0)
						} else if (index == betweenDates.size - 1) {
							val lastEventDate = dateMonthList.find { it.fullDate == date }
							lastEventDate?.apply {
								isEndDate = true
								startDate = dateModel.startDate
								endDate = dateModel.endDate
								hasEvents = true
								status = dateModel.status
							}
							//betweenDates.removeAt(betweenDates.size - 1)
						} else {
							dateMonthList.forEach {
								if (it.fullDate == date) {
									it.isMiddleDate = true
									it.hasEvents = true
									it.status = dateModel.status
								}
							}
						}
					}
				}
			}
		}

		calendarDateAdapter = CalendarDateAdapter(dateMonthList)
		val itemDecoration1 = CalendarItemDecoration(this@MainActivity)

		val layoutManager1 = GridLayoutManager(this@MainActivity, 7)

		binding.recyclerDate.apply {
			addItemDecoration(itemDecoration1)
			layoutManager = layoutManager1
			adapter = calendarDateAdapter
		}
	}

	private fun getAllDateInMonth(): List<CustomCalendar> {
		val dateList = mutableListOf<CustomCalendar>()
		val cal = mCalendar.clone() as Calendar
		val maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
		val dateFormat = SimpleDateFormat("d E M", Locale.US)
		val fullDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
		for (i in 0 until maxDay) {
			cal.set(Calendar.DAY_OF_MONTH, i + 1)
			val date = dateFormat.format(cal.time).split(" ")
			val fullDate = fullDateFormat.format(cal.time)
			dateList.add(
				CustomCalendar(
					date[0],
					date[1],
					date[2].toInt(),
					fullDate

				)
			)  // date[1] return Sun, so only taking the first letter S
		}
		val firstDateDay = dateList.first().day
		val indexOfFirstDateDay = getListOfDays().indexOfFirst {
			it.day == firstDateDay
		}
		for (i in 0 until indexOfFirstDateDay) run {
			dateList.add(i, CustomCalendar("", "", -1, ""))
		}
		return dateList
	}

	private fun setUpMonth() {
		binding.textMonthYear.text = formatDate(getCurrentDate(), outputFormat = MONTH_YEAR_FORMAT)
	}

	private fun setUpNewMonth() {
		binding.textMonthYear.text =
			formatDate(getCurrentDate(mCalendar), outputFormat = MONTH_YEAR_FORMAT)
	}

	// TODO: check new/previous year
	private fun handlePreviousMonthButtonClicked() {
		val currentMonthIndex = mCalendar.get(Calendar.MONTH)
		mCalendar.apply {
			set(Calendar.MONTH, (currentMonthIndex - 1))
			set(Calendar.DAY_OF_MONTH, 1)
		}
		setUpNewMonth()
		setupDateAdapter()
	}

	private fun handleNextMonthButtonClicked() {
		val currentMonthIndex = mCalendar.get(Calendar.MONTH)
		mCalendar.apply {
			set(Calendar.MONTH, (currentMonthIndex + 1))
			set(Calendar.DAY_OF_MONTH, 1)
		}
		setUpNewMonth()
		setupDateAdapter()
	}

	private fun getEventData(): List<EventModel> {
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
				"2019-07-05",
				"2019-07-08",
				CalendarDateAdapter.STATUS_PENDING
			)
		)
	}
}
