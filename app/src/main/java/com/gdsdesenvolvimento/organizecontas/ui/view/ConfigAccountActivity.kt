package com.gdsdesenvolvimento.organizecontas.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.di.ViewModelInjection
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityConfigAccountBinding
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigAccountAdapter
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.ConfigAccountViewModel
import com.gdsdesenvolvimento.organizecontas.utils.Constants
import com.gdsdesenvolvimento.organizecontas.utils.extensions.nextScreen
import com.gdsdesenvolvimento.organizecontas.utils.preferences.OrganizePreferences
import com.gdsdesenvolvimento.organizecontas.utils.results.FinishItemResult

class ConfigAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigAccountBinding
    private lateinit var viewModel: ConfigAccountViewModel
    private lateinit var preferences: OrganizePreferences
    private lateinit var accountAdapter: ConfigAccountAdapter
    private val callBackFinishItem = object : FinishItemResult{
        override fun finish() {
            nextScreen(ConfigCreditCardActivity())
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelInjection.getConfigAccountViewModel(this)
        initComponents()
        Log.d("caminho", "ConfigAccountActivity")

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
        accountAdapter =
            ConfigAccountAdapter(numberAccounts, DI.userKey(), DI.getRealtimeRepository(),callBackFinishItem)
    }

}