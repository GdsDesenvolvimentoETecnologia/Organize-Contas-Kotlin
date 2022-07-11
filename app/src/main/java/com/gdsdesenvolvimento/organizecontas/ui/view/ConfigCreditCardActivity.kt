package com.gdsdesenvolvimento.organizecontas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gdsdesenvolvimento.organizecontas.R

class ConfigCreditCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_credit_card)
        Log.d("caminho", "ConfigCreditCardActivity")
    }
}