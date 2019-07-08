package com.rosia.calendartest.adapter

import androidx.databinding.ViewDataBinding
import com.rosia.calendartest.R
import com.rosia.calendartest.base.BaseAdapter
import com.rosia.calendartest.base.BaseViewHolder
import com.rosia.calendartest.databinding.ItemDayBinding

class CalendarDayAdapter(
	private val data: List<String>
) : BaseAdapter<String, CalendarDayAdapter.CalendarDayViewHolder, ItemDayBinding>() {

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
			val day = item as String
			(viewBinding as ItemDayBinding).textDate.text = day
		}
	}
}