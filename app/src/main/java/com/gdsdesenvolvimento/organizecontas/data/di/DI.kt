package com.gdsdesenvolvimento.organizecontas.data.di

import android.view.View
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.LoginDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.RegisterDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.UserLoggedDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.instances.FBInstance
import com.gdsdesenvolvimento.organizecontas.data.model.User
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.ViewModelFactory

object DI {
    private val authInstance by lazy { FBInstance.getAuthenticator() }
    fun getUseLogin(email: String, password: String): User {
        return object : User {
            override var email: String = email
            override var password: String = password
        }
    }

    fun getUseRegister(name : String,email: String, password: String,phone : String): UserRegister {
        return UserRegister(name,email, password, phone)
    }

    fun getViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(getAuthRepository())
    }

    fun getAuthRepository(): AuthenticatorRepository {
        return AuthenticatorRepository(
            LoginDS(authInstance), RegisterDS(authInstance), UserLoggedDS(authInstance)
        )
    }

}