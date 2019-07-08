package com.rosia.calendartest.adapter

import androidx.databinding.ViewDataBinding
import com.rosia.calendartest.R
import com.rosia.calendartest.base.BaseAdapter
import com.rosia.calendartest.base.BaseViewHolder
import com.rosia.calendartest.databinding.ItemDayBinding
import com.rosia.calendartest.models.CustomDay

class CalendarDayAdapter(
	private val data: List<CustomDay>
) : BaseAdapter<CustomDay, CalendarDayAdapter.CalendarDayViewHolder, ItemDayBinding>() {

	override fun getItemCount(): Int = data.size

	override fun getLayoutResource(viewType: Int): Int = R.layout.item_day

	override fun getViewHolder(binding: ViewDataBinding, viewType: Int): CalendarDayViewHolder =
		CalendarDayViewHolder(binding as ItemDayBinding)

	override fun onBindCustomViewHolder(holder: CalendarDayViewHolder, position: Int) {
		val item = data[position]
		holder.bind(item)
	}

	inner class CalendarDayViewHolder(private val viewBinding: ViewDataBinding) : BaseViewHolder(viewBinding) {

		override fun bind(item: Any) {
			super.bind(item)
			val customDay = item as CustomDay
			(viewBinding as ItemDayBinding).textDate.text = customDay.dayInitial
		}
	}
}