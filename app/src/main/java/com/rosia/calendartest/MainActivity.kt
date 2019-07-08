package com.rosia.calendartest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rosia.calendartest.adapter.CalendarDateAdapter
import com.rosia.calendartest.adapter.CalendarDayAdapter
import com.rosia.calendartest.databinding.ActivityMainBinding
import com.rosia.calendartest.models.CustomCalendar
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
		calendarDateAdapter = CalendarDateAdapter(getAllDateInMonth())
		binding.recyclerDate.adapter = calendarDateAdapter
	}

	private fun getAllDateInMonth(): List<CustomCalendar> {
		val dateList = mutableListOf<CustomCalendar>()
		val cal = mCalendar.clone() as Calendar
		val maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
		val dateFormat = SimpleDateFormat("d E", Locale.US)
		for (i in 0 until maxDay) {
			cal.set(Calendar.DAY_OF_MONTH, i + 1)
			val date = dateFormat.format(cal.time).split(" ")
			dateList.add(
				CustomCalendar(
					date[0],
					date[1]
				)
			)  // date[1] return Sun, so only taking the first letter S
		}
		val firstDateDay = dateList.first().day
		val indexOfFirstDateDay = getListOfDays().indexOfFirst {
			it.day == firstDateDay
		}
		for (i in 0 until indexOfFirstDateDay) run {
			dateList.add(i, CustomCalendar("", ""))
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
}
