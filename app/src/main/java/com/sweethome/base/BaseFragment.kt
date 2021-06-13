package com.sweethome.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.fragment.app.Fragment
import com.sweethome.R
import com.sweethome.extensions.setAccessibilityClassNameButton

abstract class BaseFragment<T: MockPresenter<V>, V: MvpView> : Fragment(), MvpView {

    protected lateinit var presenter: T
    protected lateinit var mvpView: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId(), container, false);
        onViewInflated(view)
        return view
    }

    @CallSuper
    open fun onViewInflated(view: View) {
        val title = view.findViewById<TextView>(R.id.screen_title)
        title?.text = title()
        val back = view.findViewById<View>(R.id.back_button)
        back?.setOnClickListener {
            activity?.onBackPressed()
        }
        val shoppingCart = view.findViewById<View>(R.id.cart_button)
        shoppingCart.setAccessibilityClassNameButton()
        ViewCompat.setAccessibilityDelegate(shoppingCart, object: AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host:View,
                                                           info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.className = Button::class.java.name
            }
        })
        if (showCart()) {
            shoppingCart.visibility = View.VISIBLE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(mvpView)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    fun setMvpPresenter(presenter: T) {
        this.presenter = presenter
    }

    abstract fun layoutId(): Int
    abstract fun title(): String
    open fun showCart(): Boolean {
        return false
    }
}