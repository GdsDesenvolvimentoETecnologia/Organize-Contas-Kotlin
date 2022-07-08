package com.gdsdesenvolvimento.organizecontas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityConfigAccountBinding
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigAccountAdapter
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.ConfigAccountViewModel
import com.gdsdesenvolvimento.organizecontas.utils.Constants
import com.gdsdesenvolvimento.organizecontas.utils.preferences.OrganizePreferences

class ConfigAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigAccountBinding
    private lateinit var viewModel: ConfigAccountViewModel
    private lateinit var preferences: OrganizePreferences
    private lateinit var accountAdapter: ConfigAccountAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = DI.MyViewModels.getConfigAccountViewModel(this)
        initComponents()
    }

    private fun initComponents() {
        setupAdapter()
        binding.rvAccount.apply {
            adapter = accountAdapter
            layoutManager = LinearLayoutManager(this@ConfigAccountActivity,LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun setupAdapter() {
        preferences = OrganizePreferences(this)
        val numberAccounts = preferences.getIntPref(Constants.CONTA)
        accountAdapter = ConfigAccountAdapter(numberAccounts)
    }
}