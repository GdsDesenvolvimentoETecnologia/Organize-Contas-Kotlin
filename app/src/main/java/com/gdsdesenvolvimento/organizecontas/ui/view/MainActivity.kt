package com.gdsdesenvolvimento.organizecontas.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsdesenvolvimento.organizecontas.data.di.AdapterInjection
import com.gdsdesenvolvimento.organizecontas.data.di.ViewModelInjection
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityMainBinding
import com.gdsdesenvolvimento.organizecontas.ui.adapter.MainAdapterAccount
import com.gdsdesenvolvimento.organizecontas.ui.adapter.MainAdapterCreditCard
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.MainViewModel
import com.gdsdesenvolvimento.organizecontas.utils.extensions.dialog
import com.gdsdesenvolvimento.organizecontas.utils.extensions.hide
import com.gdsdesenvolvimento.organizecontas.utils.extensions.show
import com.gdsdesenvolvimento.organizecontas.utils.results.ListResult

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelInjection.getMainViewModel(this)
        setContentView(binding.root)
        observers()
    }

    private fun observers() {
        viewModel.listResults.observe(this) {
            when (it) {
                false -> {
                    dialog(this, "falha", "falhha ao carregar listas", false) {
                        finish()
                    }
                }
                true -> {
                    observersLists()
                }
            }
        }
    }

    private fun observersLists() {
        viewModel.listsResultAccount.observe(this) { resultAccount ->
            when (resultAccount) {
                is ListResult.Success -> {
                    binding.progressBarAccountList.hide()
                    binding.rvPrincipalAccount.apply {
                        adapter = AdapterInjection.getAdapterMainAccount(resultAccount.data)
                        layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    }
                }
                is ListResult.Error -> {
                    binding.progressBarAccountList.hide()
                    dialog(this,"Falha","Erro ao carregar",false){
                        finish()
                    }
                }
                is ListResult.Loading -> {
                    binding.progressBarAccountList.show()
                }
            }
        }
        viewModel.listsResultCreditCard.observe(this) { resultCreditCard ->
            when (resultCreditCard) {
                is ListResult.Success -> {
                    binding.progressBarCreditCard.hide()
                    binding.rvPrincipalCreditCard.apply {
                        adapter = AdapterInjection.getAdapterMainCreditCard(resultCreditCard.data)
                        layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    }
                }
                is ListResult.Error -> {
                    binding.progressBarCreditCard.hide()
                    dialog(this,"Falha","Erro ao carregar",false){
                        finish()
                    }
                }
                is ListResult.Loading -> {
                    binding.progressBarCreditCard.show()
                }
            }
        }
    }
}