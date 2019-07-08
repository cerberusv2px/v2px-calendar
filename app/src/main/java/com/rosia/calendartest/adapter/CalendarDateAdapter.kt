package com.rosia.calendartest.adapter

import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.rosia.calendartest.R
import com.rosia.calendartest.base.BaseAdapter
import com.rosia.calendartest.base.BaseViewHolder
import com.rosia.calendartest.databinding.ItemDateBinding
import com.rosia.calendartest.models.CustomCalendar

class CalendarDateAdapter(
	private val data: List<CustomCalendar>
) : BaseAdapter<CustomCalendar, CalendarDateAdapter.CalendarDateViewHolder, ItemDateBinding>() {

	companion object {
		const val STATUS_PENDING = "pending"
		const val STATUS_ACCEPTED = "accepted"
		const val STATUS_REJECTED = "rejected"
	}

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
			when (customCalendar.status) {
				STATUS_ACCEPTED -> handleAcceptedState(binding, customCalendar)
				STATUS_REJECTED -> handleRejectedState(binding, customCalendar)
				STATUS_PENDING -> handlePendingState(binding, customCalendar)

			}
		}

		private fun handleAcceptedState(binding: ItemDateBinding, customCalendar: CustomCalendar) {
			when {

				customCalendar.isMiddleDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rec_green))
				customCalendar.isStartDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rounded_start_green))
				customCalendar.isEndDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rounded_end_green))
				customCalendar.endDate == customCalendar.startDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_green))
			}

			binding.textDate.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white_FFFFFF))
		}

		private fun handleRejectedState(binding: ItemDateBinding, customCalendar: CustomCalendar) {
			when {
				customCalendar.isMiddleDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rec_red))
				customCalendar.isStartDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rounded_start_red))
				customCalendar.isEndDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rounded_end_red))
				customCalendar.endDate == customCalendar.startDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_red))
			}

			binding.textDate.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white_FFFFFF))
		}

		private fun handlePendingState(binding: ItemDateBinding, customCalendar: CustomCalendar) {
			when {
				customCalendar.isMiddleDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rec_grey))
				customCalendar.isStartDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rounded_start_grey))
				customCalendar.isEndDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_rounded_end_grey))
				customCalendar.endDate == customCalendar.startDate -> binding.itemContainer.background = (ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_grey))
			}

			binding.textDate.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black_2e384d))
		}
	}
}