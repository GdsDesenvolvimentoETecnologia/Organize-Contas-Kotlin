package com.gdsdesenvolvimento.organizecontas.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.utils.extensions.nextScreen

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashAction()
    }

    private fun SplashActivity.splashAction() {
        timerSplash {
            nextScreen(IntroductionActivity())
        }
    }

    private fun SplashActivity.timerSplash(function: () -> Unit) {
        Handler(Looper.myLooper()!!).postDelayed({ function() }, 3000)
    }
}