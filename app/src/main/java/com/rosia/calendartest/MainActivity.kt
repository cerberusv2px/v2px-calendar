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

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		setupDayAdapter()
		setupDateAdapter()
		setUpMonth()
	}

	private fun setupDayAdapter() {
		val dayAdapter = CalendarDayAdapter(getListOfDays())
		binding.recyclerviewDay.adapter = dayAdapter
	}

	private fun setupDateAdapter() {
		val calendar = Calendar.getInstance()
		val dateList = getAllDateInMonth(calendar)
		val calendarDateAdapter = CalendarDateAdapter(dateList)
		binding.recyclerDate.adapter = calendarDateAdapter
	}

	private fun getAllDateInMonth(calendar: Calendar): List<CustomCalendar> {
		val dateList = mutableListOf<CustomCalendar>()
		val cal = calendar.clone() as Calendar
		val maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
		val dateFormat = SimpleDateFormat("d E", Locale.US)
		for (i in 0 until maxDay) {
			cal.set(Calendar.DAY_OF_MONTH, i + 1)
			val date = dateFormat.format(cal.time).split(" ")
			dateList.add(CustomCalendar(date[0], date[1].first().toString()))  // date[1] return Sun, so only taking the first letter S
		}
		val firstDateDay = dateList.first().day
		val indexOfFirstDateDay = getListOfDays().indexOf(firstDateDay)
		for(i in 0 until indexOfFirstDateDay) run {
			dateList.add(i, CustomCalendar("", ""))
		}
		return dateList
	}

	private fun setUpMonth() {
		val currentMonth = formatDate(getCurrentDate(), outputFormat = "MMM yyyy" )
		binding.textMonthYear.text = currentMonth
	}
}
