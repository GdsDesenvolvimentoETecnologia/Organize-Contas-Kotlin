package com.gdsdesenvolvimento.organizecontas.data.di

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.LoginDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.RegisterDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.UserLoggedDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.instances.FBInstance
import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.Accounts
import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.DataUser
import com.gdsdesenvolvimento.organizecontas.data.model.User
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository
import com.gdsdesenvolvimento.organizecontas.ui.view.ConfigurationAppActivity
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.*

object DI {
    private val authInstance by lazy { FBInstance.getAuthenticator() }
    private val dbRealtime by lazy { FBInstance.getRealtimeDatabase() }
    fun getUseLogin(email: String, password: String): User {
        return object : User {
            override var email: String = email
            override var password: String = password
        }
    }
    fun userKey(): String{
        return authInstance.currentUser!!.uid
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

    fun getRealtimeRepository(): RealtimeRepository {
        return RealtimeRepository(
            DataUser(dbRealtime),
            Accounts(dbRealtime)
        )
    }

}