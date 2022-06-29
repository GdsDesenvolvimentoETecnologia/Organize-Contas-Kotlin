package com.gdsdesenvolvimento.organizecontas.utils.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.nextScreen(activity : AppCompatActivity){
    Intent(this,activity::class.java).apply {
        startActivity(this)
    }
}

fun Fragment.nextScreen(activity : AppCompatActivity){
    Intent(activity.applicationContext,activity::class.java).apply {
        startActivity(this)
    }
}