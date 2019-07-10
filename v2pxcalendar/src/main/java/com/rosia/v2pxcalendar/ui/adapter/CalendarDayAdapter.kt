package com.rosia.v2pxcalendar.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rosia.v2pxcalendar.R
import com.rosia.v2pxcalendar.models.CustomDay
import kotlinx.android.synthetic.main.item_day.view.*

class CalendarDayAdapter(
	private val data: List<CustomDay>
) : RecyclerView.Adapter<CalendarDayAdapter.CalendarDayViewHolder>() {

	override fun getItemCount(): Int = data.size

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDayViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
		return CalendarDayViewHolder(view)
	}

	override fun onBindViewHolder(holder: CalendarDayViewHolder, position: Int) {
		val item = data[position]
		holder.bind(item)
	}

	inner class CalendarDayViewHolder(itemView: View) :
		RecyclerView.ViewHolder(itemView) {

		fun bind(item: CustomDay) {
			itemView.text_date.text = item.dayInitial
		}
	}
}