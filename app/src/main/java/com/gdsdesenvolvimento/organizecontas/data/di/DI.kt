package com.gdsdesenvolvimento.organizecontas.data.di

import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.LoginDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.RegisterDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.UserLoggedDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.instances.FBInstance
import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.Accounts
import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.CreditCard
import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.DataUser
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.data.model.User
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigAccountAdapter
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigCreditCardAdapter
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.*
import com.gdsdesenvolvimento.organizecontas.utils.results.FinishItemResult
import com.gdsdesenvolvimento.organizecontas.utils.results.SaveFormResult

object DI {
    private val authInstance by lazy { FBInstance.getAuthenticator() }
    private val dbRealtime by lazy { FBInstance.getRealtimeDatabase() }
    fun getUseLogin(email: String, password: String): User {
        return object : User {
            override var email: String = email
            override var password: String = password
        }
    }

    fun userKey(): String {
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

    private fun getRealtimeRepository(): RealtimeRepository {
        return RealtimeRepository(
            DataUser(dbRealtime),
            Accounts(dbRealtime),
            CreditCard(dbRealtime)
        )
    }

    fun creditCardAdapter(
        qtdForms: Int,
        finishItemResult: FinishItemResult,
        result: SaveFormResult
    ): ConfigCreditCardAdapter {
        return ConfigCreditCardAdapter(
            qtdForms,
            userKey(),
            getRealtimeRepository(),
            finishItemResult,
            result
        )
    }

    fun accountAdapter(
        qtdForms: Int,
        finishItemResult: FinishItemResult,
        result: SaveFormResult

    ): ConfigAccountAdapter {
        return ConfigAccountAdapter(
            qtdForms,
            userKey(),
            getRealtimeRepository(),
            finishItemResult,
            result
        )
    }

    fun getFormAccount(
        nameBank: String,
        value: Double,
        ck: Boolean,
        valorLimit: Double
    ): ItemAccountForm {
        return ItemAccountForm(
            nameBank, value, ck, valorLimit
        )
    }

    fun getFormCreditCard(
        nameBank: String,
        value: Double,
        ck: Boolean,
        valorLimit: Double
    ): ItemCreditCardForm {
        return ItemCreditCardForm(
            nameBank, value, ck, valorLimit
        )
    }
}