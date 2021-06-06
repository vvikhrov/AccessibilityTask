package com.sweethome.base.selector

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweethome.R
import com.sweethome.utils.StartEndOffsetItemDecoration


class HorizontalSelector(context: Context, attributeSet: AttributeSet): RecyclerView(context, attributeSet) {

    private val selectorAdapter: SelectorAdapter = SelectorAdapter()

    init {
        addItemDecoration(StartEndOffsetItemDecoration(context.resources.getDimensionPixelOffset(R.dimen.catalog_offset)))
        layoutManager = object : LinearLayoutManager(context, HORIZONTAL, false) {
            override fun checkLayoutParams(lp: LayoutParams): Boolean {
                lp.width = width - resources.getDimensionPixelOffset(R.dimen.next_item_visible_part_width)
                return true
            }
        }
        adapter = selectorAdapter
    }

    fun updateValues(values: ArrayList<SelectorItemModel>) {
        selectorAdapter.updateValues(values)
    }

    fun setCheckedChangeListener(changeListener: CheckedChangeListener) {
        selectorAdapter.setCheckedChangeListener(changeListener)
    }
}