package com.rosia.calendartest.adapter

import androidx.databinding.ViewDataBinding
import com.rosia.calendartest.R
import com.rosia.calendartest.base.BaseAdapter
import com.rosia.calendartest.base.BaseViewHolder
import com.rosia.calendartest.databinding.ItemDateBinding
import com.rosia.calendartest.models.CustomCalendar

class CalendarDateAdapter(
	private val data: List<CustomCalendar>
) : BaseAdapter<CustomCalendar, CalendarDateAdapter.CalendarDateViewHolder, ItemDateBinding>() {

	override fun getItemCount(): Int = data.size

	override fun getLayoutResource(viewType: Int): Int = R.layout.item_date

	override fun getViewHolder(binding: ViewDataBinding, viewType: Int): CalendarDateViewHolder =
		CalendarDateViewHolder(binding as ItemDateBinding)

	override fun onBindCustomViewHolder(holder: CalendarDateViewHolder, position: Int) {
		val item = data[position]
		holder.bind(item)
	}

	inner class CalendarDateViewHolder(private val viewBinding: ViewDataBinding) :
		BaseViewHolder(viewBinding) {

		override fun bind(item: Any) {
			super.bind(item)
			val customCalendar = item as CustomCalendar
			(binding as ItemDateBinding).textDate.text = customCalendar.date
		}
	}
}