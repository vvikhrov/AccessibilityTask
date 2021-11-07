package com.sweethome

import android.app.Application
import com.merseyside.utils.ThemeManager

class SweetHomeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        ThemeManager.setTheme(ThemeManager.Theme.LIGHT)
        ThemeManager.apply()
    }

    companion object {
        private var instance: SweetHomeApplication? = null

        fun getInstance(): SweetHomeApplication {
            return instance ?: throw UninitializedPropertyAccessException()
        }
    }
}