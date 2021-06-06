package com.sweethome.base

import androidx.annotation.CallSuper
import com.sweethome.MockLoader

open class MockPresenter<T: MvpView> constructor(val mockLoader: MockLoader) {

    protected var mvpView: T? = null

    @CallSuper
    open fun attach(mvpView: T) {
        this.mvpView = mvpView
    }

    open fun detach() {
        this.mvpView = null
    }
}