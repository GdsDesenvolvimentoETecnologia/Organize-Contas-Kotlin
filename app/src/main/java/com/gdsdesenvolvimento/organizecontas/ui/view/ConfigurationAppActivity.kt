package com.gdsdesenvolvimento.organizecontas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityConfigurationAppBinding
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.ConfigurationAppViewModel
import com.gdsdesenvolvimento.organizecontas.utils.Constants
import com.gdsdesenvolvimento.organizecontas.utils.extensions.*
import com.gdsdesenvolvimento.organizecontas.utils.preferences.OrganizePreferences

class ConfigurationAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigurationAppBinding
    private lateinit var viewModel: ConfigurationAppViewModel
    private lateinit var preferences: OrganizePreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = DI.MyViewModels.getConfigViewModel(this)
        initComponents()
        observers()
    }

    private fun initComponents() {
        preferences = OrganizePreferences(this)
        binding.numeroContaConrrente.afterTextChanged {
            if (it.isNotEmpty()) {
                viewModel.numberIsValid(it.toInt())
            } else {
                setEditError(binding.numeroContaConrrente, getString(R.string.campo_em_branco))
            }
        }
        binding.numeroCartaoCredito.afterTextChanged {
            if (it.isNotEmpty()) {
                viewModel.numberIsValid(it.toInt())
            }
        }
        binding.btnContinuar.setOnClickListener {
            preferences.setIntPref(
                Constants.CONTA,
                binding.numeroContaConrrente.extractInt()
            )
            preferences.setIntPref(
                Constants.CARTAO,
                binding.numeroCartaoCredito.extractInt()
            )
            nextScreen(ConfigAccountActivity())
        }
    }

    private fun observers() {
        viewModel.settings.observe(this) { result ->
            when (result) {
                false -> {
                    onFailed()
                }
                true -> {
                    onSuccess()
                }
            }
        }
    }

    private fun onFailed() {
        binding.btnContinuar.isEnabled = false
        setEditError(binding.numeroContaConrrente, getString(R.string.valor_invalido))
    }

    private fun onSuccess() {
        binding.btnContinuar.isEnabled = true
    }
}