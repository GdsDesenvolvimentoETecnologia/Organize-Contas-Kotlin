package com.gdsdesenvolvimento.organizecontas.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.data.di.AdapterInjection
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityConfigAccountBinding
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigAccountAdapter
import com.gdsdesenvolvimento.organizecontas.utils.Constants
import com.gdsdesenvolvimento.organizecontas.utils.extensions.dialog
import com.gdsdesenvolvimento.organizecontas.utils.extensions.message
import com.gdsdesenvolvimento.organizecontas.utils.extensions.nextScreen
import com.gdsdesenvolvimento.organizecontas.utils.preferences.OrganizePreferences
import com.gdsdesenvolvimento.organizecontas.utils.results.FinishItemResult
import com.gdsdesenvolvimento.organizecontas.utils.results.SaveFormResult

class ConfigAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigAccountBinding
    private lateinit var preferences: OrganizePreferences
    private lateinit var accountAdapter: ConfigAccountAdapter
    private val callBackFinishItem = object : FinishItemResult {
        override fun finish() {
            nextScreen(ConfigCreditCardActivity())
        }
    }
    private val resultSaveForm = object : SaveFormResult {
        override fun success() {
            message("Formulario salvo")
        }

        override fun failed(message: String) {
            dialog(
                this@ConfigAccountActivity,
                getString(R.string.falha),
                "Nao foi possivel salvar dados do formulario"
            ){
                return@dialog
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        configAdapter()
        binding.rvAccount.apply {
            adapter = accountAdapter
            layoutManager =
                LinearLayoutManager(this@ConfigAccountActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun configAdapter() {
        preferences = OrganizePreferences(this)
        val numberAccounts = preferences.getIntPref(Constants.CONTA)
        accountAdapter = AdapterInjection.accountAdapter(numberAccounts, callBackFinishItem,resultSaveForm)
    }

}