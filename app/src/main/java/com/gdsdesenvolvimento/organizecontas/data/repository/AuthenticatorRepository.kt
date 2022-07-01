package com.gdsdesenvolvimento.organizecontas.data.repository

import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.LoginDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.RegisterDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.UserLoggedDS

class AuthenticatorRepository(
    private val loginDS: LoginDS,
    private val registerDS: RegisterDS,
    private val userDS: UserLoggedDS
) {
    //login
    suspend fun loginEmailWithPass(email : String,password :String) = loginDS.loginUserEmailWithPassword(email, password)
    suspend fun loginAnonymous() = loginDS.loginUserAnonymous()
    //Register
    suspend fun registerEmailWithPass(email: String,password: String) = registerDS.registerUserEmailWithPassword(email, password)
    //UserLogged
    suspend fun logout() = userDS.logout()
}