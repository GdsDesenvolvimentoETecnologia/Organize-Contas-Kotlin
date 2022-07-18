package com.gdsdesenvolvimento.organizecontas.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsdesenvolvimento.organizecontas.data.di.AdapterInjection
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.di.ViewModelInjection
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityMainBinding
import com.gdsdesenvolvimento.organizecontas.ui.adapter.MainAdapterAccount
import com.gdsdesenvolvimento.organizecontas.ui.adapter.MainAdapterCreditCard
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.MainViewModel
import com.gdsdesenvolvimento.organizecontas.utils.Constants
import com.gdsdesenvolvimento.organizecontas.utils.results.ListResult

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var creditCardAdapter: MainAdapterCreditCard
    private lateinit var accountAdapter: MainAdapterAccount
    private lateinit var viewModel: MainViewModel

    private var listCreditCard: ArrayList<ItemCreditCardForm> = arrayListOf()
    private var listAccount: ArrayList<ItemAccountForm> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelInjection.getMainViewModel(this)
        setContentView(binding.root)
        fetchLists()
        observers()
    }

    private fun fetchLists() {
        val qtdCard = qtdCardPreferences()
        val qtdAccount = qtdAccountPreferences()
        viewModel.fetchLists(qtdAccount, qtdCard)
    }

    private fun qtdAccountPreferences(): Int {
        return DI.getPreferences(this).getIntPref(Constants.CONTA)
    }

    private fun qtdCardPreferences(): Int {
        return DI.getPreferences(this).getIntPref(Constants.CARTAO)
    }

    private fun observers() {
        viewModel.listsResultCreditCard.observe(this) {
            when (it) {
                is ListResult.Success -> {
                    listCreditCard.addAll(it.data.first)
                }
                is ListResult.Error -> {

                }
                is ListResult.Loading -> {

                }
            }
        }
        viewModel.listsResultAccount.observe(this) {
            when (it) {
                is ListResult.Success -> {
                    listAccount.addAll(it.data.second)
                }
                is ListResult.Error -> {

                }
                is ListResult.Loading -> {

                }
            }
        }
        setupAdapters()
    }

    private fun setupAdapters() {
        creditCardAdapter = AdapterInjection.getAdapterMainCreditCard(listCreditCard)
        accountAdapter = AdapterInjection.getAdapterMainAccount(listAccount)
        initComponents()
    }

    private fun initComponents() {
        binding.rvPrincipalAccount.apply {
            adapter = accountAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.rvPrincipalCreditCard.apply {
            adapter = creditCardAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

}