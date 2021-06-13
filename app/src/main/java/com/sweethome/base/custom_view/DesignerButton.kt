package com.sweethome.base.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.sweethome.R

class DesignerButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val nameTextView: TextView

    init {
        inflate(context, R.layout.view_designer_button, this)
        isClickable = true
        isFocusable = true
        nameTextView = findViewById(R.id.designer)

        ViewCompat.setAccessibilityDelegate(this, object: AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host: View,
                                                           info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.className = Button::class.java.name
            }
        })
    }

    fun setDesignerName(name: String) {
        nameTextView.text = name
    }

}
