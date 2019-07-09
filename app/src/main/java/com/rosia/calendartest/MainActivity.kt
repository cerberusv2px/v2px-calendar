package com.rosia.calendartest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rosia.calendartest.databinding.ActivityMainBinding
import com.rosia.v2pxcalendar.ui.CalendarView

class MainActivity : AppCompatActivity() {

	private lateinit var mbinding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//		setContentView(R.layout.activity_main)
//		CalendarView(this@MainActivity)
	}

}
