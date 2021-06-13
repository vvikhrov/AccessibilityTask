package com.sweethome.base.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.sweethome.R

class CartButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var cartItemsAmount: TextView

    init {
        inflate(context, R.layout.view_cart_button, this)
        isClickable = true
        isFocusable = true
        cartItemsAmount = findViewById(R.id.items_count)

        ViewCompat.setAccessibilityDelegate(this, object: AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host:View,
                                                           info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.className = Button::class.java.name
            }
        })
    }

    fun setItemCount(itemsCount: Int) {
        if (itemsCount == 0) {
            cartItemsAmount.visibility = View.GONE
        } else {
            cartItemsAmount.visibility = View.VISIBLE
            cartItemsAmount.text = itemsCount.toString()
        }
        val goodsCountText = resources.getQuantityString(R.plurals.d_goods, itemsCount, itemsCount)
        contentDescription = "${context.getString(R.string.cart_title)}, $goodsCountText"
    }
}
