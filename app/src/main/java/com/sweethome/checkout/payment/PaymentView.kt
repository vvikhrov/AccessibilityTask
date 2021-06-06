package com.sweethome.checkout.payment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sweethome.R

class PaymentView(context: Context, attributeSet: AttributeSet): LinearLayout(context, attributeSet) {
    init {
        LayoutInflater.from(context).inflate(R.layout.payment_view_layout, this, true)
    }
}