package com.sweethome.base.selector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.recyclerview.widget.RecyclerView
import com.sweethome.R

class SelectorAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private val itemsList = arrayListOf<SelectorItemModel>()
    private var checkedChangeListener: CheckedChangeListener? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.selectable_item_layout, parent, false)
        )
    }

    fun updateValues(items: ArrayList<SelectorItemModel>) {
        itemsList.clear()
        itemsList.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemsList[position]
        holder.title.text = item.title
        holder.subtitle.text = item.subtitle
        holder.icon.setImageResource(item.imageId)
        holder.checkbox.isChecked = item.checked

        holder.checkbox.setOnClickListener {
            checkedChangeListener?.onCheckedChange(
                item,
                !item.checked
            )
        }

        holder.itemView.setOnClickListener {
            checkedChangeListener?.onCheckedChange(
                item,
                !item.checked
            )
        }
    }

    fun setCheckedChangeListener(changeListener: CheckedChangeListener) {
        this.checkedChangeListener = changeListener
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val subtitle: TextView = itemView.findViewById(R.id.subtitle)
    val icon: ImageView = itemView.findViewById(R.id.icon)
    val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)

    init {
        ViewCompat.setAccessibilityDelegate(itemView, object: AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host: View,
                                                           info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.className = CheckBox::class.java.name
                info.isCheckable = true
                info.isChecked = checkbox.isChecked
            }
        })



    }
}

interface CheckedChangeListener {
    fun onCheckedChange(item: SelectorItemModel, checked: Boolean)
}
