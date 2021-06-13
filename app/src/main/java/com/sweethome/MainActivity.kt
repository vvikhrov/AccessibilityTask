package com.sweethome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var rootRouter: RootRouterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootRouter = RootRouterImpl(
            supportFragmentManager,
            CartRepository(),
            MockLoader(Gson(), assets)
        )
        rootRouter.showCatalog()
    }

    override fun onBackPressed() {
        if (rootRouter.onBackPressed()) {
            return
        }
        finish()
    }
}