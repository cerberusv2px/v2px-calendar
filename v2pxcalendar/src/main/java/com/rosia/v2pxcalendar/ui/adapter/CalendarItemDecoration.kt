package com.rosia.v2pxcalendar.ui.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rosia.v2pxcalendar.ui.adapter.CalendarDateAdapter

class CalendarItemDecoration(var context: Context): RecyclerView.ItemDecoration(){
	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
		super.getItemOffsets(outRect, view, parent, state)
		val adapterPosition = parent.getChildAdapterPosition(view)
		val item = (parent.adapter as CalendarDateAdapter).data[adapterPosition]
		outRect.left = 0
		outRect.right = 0
		outRect.top = 2
		outRect.bottom = 2

		if(item.isEndDate) {
			outRect.left = 0
			outRect.right = 16
			outRect.top = 2
			outRect.bottom = 2
		}

	}

}