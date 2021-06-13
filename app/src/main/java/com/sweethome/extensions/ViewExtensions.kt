package com.sweethome.extensions

import android.view.View
import android.widget.Button
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat

fun <T : View> View.setAccessibilityClassName(clazz: Class<T>) {
    ViewCompat.setAccessibilityDelegate(this, object: AccessibilityDelegateCompat() {
        override fun onInitializeAccessibilityNodeInfo(host:View,
                                                       info: AccessibilityNodeInfoCompat
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            info.className = clazz.name
        }
    })
}

fun View.setAccessibilityClassNameButton() {
    this.setAccessibilityClassName(Button::class.java)
}