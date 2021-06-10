package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_splash_screen)

        Handler().postDelayed({
            Intent(this@SplashScreen, LoginActivity::class.java).also{
                startActivity(it)
                finish()
            }
        }, 4000)
    }


}