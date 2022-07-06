package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository

class ViewModelFactory(private val authenticatorRepository: AuthenticatorRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(authenticatorRepository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(authenticatorRepository) as T
            else -> throw IllegalStateException(FALHA_VIEWMODEL)
        }
    }

    companion object {
        const val FALHA_VIEWMODEL = "Falha ao iniciar o viewmodel"
    }

}