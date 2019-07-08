package com.rosia.calendartest.base

import androidx.databinding.ViewDataBinding


open class BaseViewHolder(open val binding: ViewDataBinding) :
	androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

	open fun bind(item: Any) {
		//binding.setVariable(BR.item, item)
		binding.executePendingBindings()
	}
}