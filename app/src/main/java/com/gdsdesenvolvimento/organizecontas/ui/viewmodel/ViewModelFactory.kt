package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val authenticatorRepository: AuthenticatorRepository,
    private val realtimeRepository: RealtimeRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                authenticatorRepository
            ) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(
                authenticatorRepository,
                realtimeRepository
            ) as T
            modelClass.isAssignableFrom(ConfigurationAppViewModel::class.java) -> ConfigurationAppViewModel() as T
            else -> throw IllegalStateException(FALHA_VIEWMODEL)
        }
    }

    companion object {
        const val FALHA_VIEWMODEL = "Falha ao iniciar o viewmodel"
    }

}