package com.rosia.v2pxcalendar.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rosia.v2pxcalendar.R
import com.rosia.v2pxcalendar.ui.models.CustomCalendar
import kotlinx.android.synthetic.main.item_date.view.*

class CalendarDateAdapter(val data: List<CustomCalendar>) :
	RecyclerView.Adapter<CalendarDateAdapter.CalendarDateViewHolder>() {

	companion object {
		const val STATUS_PENDING = "pending"
		const val STATUS_ACCEPTED = "accepted"
		const val STATUS_REJECTED = "rejected"
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDateViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false)
		return CalendarDateViewHolder(view)
	}

	override fun getItemCount(): Int {
		return data.size
	}

	override fun onBindViewHolder(holder: CalendarDateViewHolder, position: Int) {
		val item = data[position]
		holder.bind(item)
	}

	inner class CalendarDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		fun bind(customCalendar: CustomCalendar) {
			itemView.text_date.text = customCalendar.date
			when (customCalendar.status) {
				STATUS_ACCEPTED -> handleAcceptedState(customCalendar)
				STATUS_REJECTED -> handleRejectedState(customCalendar)
				STATUS_PENDING -> handlePendingState(customCalendar)

			}
		}

		private fun handleAcceptedState(customCalendar: CustomCalendar) {
			when {

				customCalendar.isMiddleDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(itemView.context, R.drawable.bg_rec_green))

				customCalendar.isStartDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(itemView.context, R.drawable.bg_rounded_start_green))

				customCalendar.isEndDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(
						itemView.context,
						R.drawable.bg_rounded_end_green
					))

				customCalendar.endDate == customCalendar.startDate -> itemView.text_date.background =
					(ContextCompat.getDrawable(itemView.context, R.drawable.bg_circle_green))
			}

			itemView.text_date.setTextColor(
				ContextCompat.getColor(
					itemView.context,
					R.color.white_FFFFFF
				)
			)
		}

		private fun handleRejectedState(customCalendar: CustomCalendar) {
			when {
				customCalendar.isMiddleDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(itemView.context, R.drawable.bg_rec_red))

				customCalendar.isStartDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(
						itemView.context,
						R.drawable.bg_rounded_start_red
					))

				customCalendar.isEndDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(itemView.context, R.drawable.bg_rounded_end_red))

				customCalendar.endDate == customCalendar.startDate -> itemView.text_date.background =
					(ContextCompat.getDrawable(itemView.context, R.drawable.bg_circle_red))
			}

			itemView.text_date.setTextColor(
				ContextCompat.getColor(
					itemView.context,
					R.color.white_FFFFFF
				)
			)
		}

		private fun handlePendingState(customCalendar: CustomCalendar) {
			when {
				customCalendar.isMiddleDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(itemView.context, R.drawable.bg_rec_grey))
				customCalendar.isStartDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(
						itemView.context,
						R.drawable.bg_rounded_start_grey
					))
				customCalendar.isEndDate -> itemView.item_container.background =
					(ContextCompat.getDrawable(
						itemView.context,
						R.drawable.bg_rounded_end_grey
					))
				customCalendar.endDate == customCalendar.startDate -> itemView.text_date.background =
					(ContextCompat.getDrawable(itemView.context, R.drawable.bg_circle_grey))
			}

			itemView.text_date.setTextColor(
				ContextCompat.getColor(
					itemView.context,
					R.color.black_2e384d
				)
			)
		}
	}
}