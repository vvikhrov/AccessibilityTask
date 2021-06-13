package com.sweethome.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatImageView

class SquareImageView(context: Context, attributeSet: AttributeSet): AppCompatImageView(context, attributeSet) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width)
    }

    override fun getAccessibilityClassName(): CharSequence {
        return CheckBox::class.java.name
    }
}