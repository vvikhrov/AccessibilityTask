package com.sweethome.checkout.delivery

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.sweethome.R

class DeliveryItemView(context: Context, attributeSet: AttributeSet?) :
    FrameLayout(context, attributeSet) {
    constructor(context: Context) : this(context, null)

    var company: TextView
    var time: TextView
    var price: TextView
    var toggle: ImageView

    var chosen: Boolean = false

    var onChosenListener: OnChosenListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.delivery_type_item, this, true)

        company = findViewById(R.id.company)
        time = findViewById(R.id.time)
        price = findViewById(R.id.price)
        toggle = findViewById(R.id.toggle)
    }

    fun update(item: DeliveryViewModel) {
        val stringBuilder = StringBuilder()
        company.text = item.name
        stringBuilder.append(item.name)
        if (item.timeFrom == item.timeTo) {
            time.text = resources.getString(R.string.delivery_time)
            stringBuilder.append(resources.getString(R.string.delivery_time))
        } else {
            time.text = resources.getString(R.string.time_from_to, item.timeFrom, item.timeTo)
            stringBuilder.append(resources.getString(R.string.time_from_to, item.timeFrom, item.timeTo))
        }
        price.text = item.price
        stringBuilder.append(item.price)
        val event = AccessibilityEvent.obtain()
        if (item.chosen)  {
            chosen = true
            toggle.setImageResource(R.drawable.ic_toggle_checked)
        } else {
            chosen = false
            toggle.setImageResource(R.drawable.ic_toggle_unchecked)
        }
        this.setOnClickListener {
            onChosenListener?.onItemChosen(item.id)
        }
        contentDescription = stringBuilder.toString()

        ViewCompat.setAccessibilityDelegate(this, object: AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host: View,
                                                           info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.className = CheckBox::class.java.name
                info.isCheckable = true
                info.isChecked = item.chosen
            }
        })
    }

    override fun getAccessibilityClassName(): CharSequence {
        return CheckBox::class.java.name
    }
}

interface OnChosenListener {
    fun onItemChosen(id: String)
}
