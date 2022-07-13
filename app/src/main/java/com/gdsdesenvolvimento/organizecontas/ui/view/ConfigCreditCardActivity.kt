package com.gdsdesenvolvimento.organizecontas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.data.di.AdapterInjection
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityConfigCreditCardBinding
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigCreditCardAdapter
import com.gdsdesenvolvimento.organizecontas.utils.Constants
import com.gdsdesenvolvimento.organizecontas.utils.extensions.dialog
import com.gdsdesenvolvimento.organizecontas.utils.extensions.message
import com.gdsdesenvolvimento.organizecontas.utils.extensions.nextScreen
import com.gdsdesenvolvimento.organizecontas.utils.preferences.OrganizePreferences
import com.gdsdesenvolvimento.organizecontas.utils.results.FinishItemResult
import com.gdsdesenvolvimento.organizecontas.utils.results.SaveFormResult

class ConfigCreditCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigCreditCardBinding
    private lateinit var creditCardAdapter: ConfigCreditCardAdapter
    private lateinit var preferences: OrganizePreferences
    private val callBackFinishItem = object : FinishItemResult {
        override fun finish() {
            nextScreen(MainActivity())
        }
    }

    private val resultSaveForm = object : SaveFormResult {
        override fun success() {
            message("Formulario salvo")
        }

        override fun failed(message: String) {
            dialog(
                this@ConfigCreditCardActivity,
                getString(R.string.falha),
                "Nao foi possivel salvar dados do formulario"
            ){
                return@dialog
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigCreditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        setupAdapter()
        binding.rvCreditCard.apply {
            adapter = creditCardAdapter
            layoutManager = LinearLayoutManager(
                this@ConfigCreditCardActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun setupAdapter() {
        preferences = OrganizePreferences(this)
        val qtdCreditCard = preferences.getIntPref(Constants.CARTAO)
        creditCardAdapter = AdapterInjection.creditCardAdapter(qtdCreditCard, callBackFinishItem,resultSaveForm)

    }
}