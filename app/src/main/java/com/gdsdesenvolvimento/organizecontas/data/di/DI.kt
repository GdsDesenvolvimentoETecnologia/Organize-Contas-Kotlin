package com.gdsdesenvolvimento.organizecontas.data.di

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.LoginDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.RegisterDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.UserLoggedDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.instances.FBInstance
import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.DataUser
import com.gdsdesenvolvimento.organizecontas.data.model.User
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository
import com.gdsdesenvolvimento.organizecontas.ui.view.ConfigurationAppActivity
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.*

object DI {
    private val authInstance by lazy { FBInstance.getAuthenticator() }
    fun getUseLogin(email: String, password: String): User {
        return object : User {
            override var email: String = email
            override var password: String = password
        }
    }

    fun getUseRegister(name: String, email: String, password: String, phone: String): UserRegister {
        return UserRegister(nome = name, email = email, password = password, phone = phone)
    }

    fun getViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(getAuthRepository(), getRealtimeRepository())
    }

    fun isUserLogged(): Boolean {
        return FBInstance.getAuthenticator().currentUser != null
    }

    private fun getAuthRepository(): AuthenticatorRepository {
        return AuthenticatorRepository(
            LoginDS(authInstance), RegisterDS(authInstance), UserLoggedDS(authInstance)
        )
    }

    private fun getRealtimeRepository(): RealtimeRepository {
        return RealtimeRepository(
            DataUser(FBInstance.getRealtimeDatabase())
        )
    }

    object MyViewModels {
        fun getRegisterViewModel(owner: ViewModelStoreOwner): RegisterViewModel {
            return ViewModelProvider(owner, getViewModelFactory())[RegisterViewModel::class.java]
        }

        fun getLoginViewModel(owner: ViewModelStoreOwner): LoginViewModel {
            return ViewModelProvider(owner, getViewModelFactory())[LoginViewModel::class.java]
        }

        fun getConfigViewModel(owner: ViewModelStoreOwner): ConfigurationAppViewModel {
            return ViewModelProvider(
                owner,
                getViewModelFactory()
            )[ConfigurationAppViewModel::class.java]
        }

        fun getConfigAccountViewModel(owner: ViewModelStoreOwner): ConfigAccountViewModel {
            return ViewModelProvider(
                owner,
                getViewModelFactory()
            )[ConfigAccountViewModel::class.java]
        }

        fun getConfigCreditCardViewModel(owner: ViewModelStoreOwner): ConfigCreditCardViewModel {
            return ViewModelProvider(
                owner,
                getViewModelFactory()
            )[ConfigCreditCardViewModel::class.java]
        }
    }
}