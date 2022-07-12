package com.gdsdesenvolvimento.organizecontas.data.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.*

object ViewModelInjection {
    fun getRegisterViewModel(owner: ViewModelStoreOwner): RegisterViewModel {
        return ViewModelProvider(owner, DI.getViewModelFactory())[RegisterViewModel::class.java]
    }

    fun getLoginViewModel(owner: ViewModelStoreOwner): LoginViewModel {
        return ViewModelProvider(owner, DI.getViewModelFactory())[LoginViewModel::class.java]
    }

    fun getConfigViewModel(owner: ViewModelStoreOwner): ConfigurationAppViewModel {
        return ViewModelProvider(
            owner,
            DI.getViewModelFactory()
        )[ConfigurationAppViewModel::class.java]
    }
}