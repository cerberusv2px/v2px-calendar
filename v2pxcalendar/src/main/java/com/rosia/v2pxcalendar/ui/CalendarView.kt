package com.rosia.v2pxcalendar.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.rosia.v2pxcalendar.R
import com.rosia.v2pxcalendar.models.CustomCalendar
import com.rosia.v2pxcalendar.models.EventModel
import com.rosia.v2pxcalendar.properties.CalendarProperties
import com.rosia.v2pxcalendar.ui.adapter.CalendarDateAdapter
import com.rosia.v2pxcalendar.ui.adapter.CalendarDayAdapter
import com.rosia.v2pxcalendar.ui.adapter.CalendarItemDecoration
import com.rosia.v2pxcalendar.utils.formatDate
import com.rosia.v2pxcalendar.utils.getBetweenDates
import com.rosia.v2pxcalendar.utils.getCurrentDate
import com.rosia.v2pxcalendar.utils.getListOfDays
import kotlinx.android.synthetic.main.view_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalendarView : ConstraintLayout {

	private lateinit var calendarDateAdapter: CalendarDateAdapter
	private lateinit var calendar: Calendar
	private lateinit var calendarProps: CalendarProperties
	private var eventsDates = listOf<EventModel>()

	companion object {
		private const val MONTH_YEAR_FORMAT = "MMMM yyyy"
	}

	constructor(context: Context, calendarProperties: CalendarProperties) : super(context) {
		this.calendarProps = calendarProperties
		initView()
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		initView()
	}

	private fun setAttributes(context: Context, attrs: AttributeSet) {
		val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalendarView)
		try {
			initCalProps(typedArray)
		} finally {
			typedArray.recycle()
		}
	}

	private fun initCalProps(typedArray: TypedArray) {
		val headerColor = typedArray.getColor(R.styleable.CalendarView_headerColor, 0)
	}

	private fun setupDayAdapter() {
		val dayAdapter = CalendarDayAdapter(getListOfDays())
		recyclerview_day.adapter = dayAdapter
	}

	private fun setupDateAdapter() {
		val dateMonthList = getAllDateInMonth() as MutableList
		val eventList = this.eventsDates
		for (customCalendar in dateMonthList) {
			val eventDate = eventList.find { it.startDate == customCalendar.fullDate }
			if (eventDate != null) {
				customCalendar.startDate = eventDate.startDate
				customCalendar.endDate = eventDate.endDate
				customCalendar.hasEvents = true
				customCalendar.status = eventDate.status
			}
		}

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
						} else if (index == betweenDates.size - 1) {
							val lastEventDate = dateMonthList.find { it.fullDate == date }
							lastEventDate?.apply {
								isEndDate = true
								startDate = dateModel.startDate
								endDate = dateModel.endDate
								hasEvents = true
								status = dateModel.status
							}
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

		recycler_date.apply {
			adapter = calendarDateAdapter
		}

		calendarDateAdapter.notifyDataSetChanged()
	}

	private fun setUpMonth() {
		textMonthYear.text = formatDate(getCurrentDate(), outputFormat = MONTH_YEAR_FORMAT)
	}

	private fun setUpNewMonth() {
		textMonthYear.text =
			formatDate(getCurrentDate(calendar), outputFormat = MONTH_YEAR_FORMAT)
	}

	private fun handlePreviousMonthButtonClicked() {
		val currentMonthIndex = calendar.get(Calendar.MONTH)
		calendar.apply {
			set(Calendar.MONTH, (currentMonthIndex - 1))
			set(Calendar.DAY_OF_MONTH, 1)
		}
		setUpNewMonth()
		setupDateAdapter()
	}

	private fun handleNextMonthButtonClicked() {
		val currentMonthIndex = calendar.get(Calendar.MONTH)
		calendar.apply {
			set(Calendar.MONTH, (currentMonthIndex + 1))
			set(Calendar.DAY_OF_MONTH, 1)
		}
		setUpNewMonth()
		setupDateAdapter()
	}

	private fun getAllDateInMonth(): List<CustomCalendar> {
		val dateList = mutableListOf<CustomCalendar>()
		val cal = calendar.clone() as Calendar
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

	private fun initView() {
		val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_calendar, this)

		calendar = Calendar.getInstance()

		btn_previous.setOnClickListener { handlePreviousMonthButtonClicked() }
		btn_next.setOnClickListener { handleNextMonthButtonClicked() }

		val gridLayoutManager = GridLayoutManager(context, 7)
		val customItemDecoration = CalendarItemDecoration(context)

		recycler_date.apply {
			layoutManager = gridLayoutManager
			addItemDecoration(customItemDecoration)
		}

		setupDayAdapter()

		setUpMonth()
	}

	fun setEvents(eventList: List<EventModel>): CalendarView {
		this.eventsDates = eventList
		return this
	}

	fun show(): CalendarView {
		setupDateAdapter()
		return this
	}
}