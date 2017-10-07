package com.krishna.kakadiya.pizzaapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

/**
 * @author Krishna Kakadiya
 * *
 * @date 10-7-2017
 * *
 * @file SplashActivity.java
 */


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_view)

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, PizzaActivity::class.java)
            startActivity(intent)
            finish()
        }, Constant.SPLASH_TIMEOUT)
    }
}
