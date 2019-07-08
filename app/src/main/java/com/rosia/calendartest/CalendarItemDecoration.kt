package com.rosia.calendartest

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class CalendarItemDecoration(var context: Context): RecyclerView.ItemDecoration(){
	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
		super.getItemOffsets(outRect, view, parent, state)
		val adapterPosition = parent?.getChildAdapterPosition(view)
		outRect.left = 2
		outRect.right = 0
		outRect.top = 2
		outRect.bottom = 2

	}

}