package com.gdsdesenvolvimento.organizecontas.data.dataSource.auth

import com.gdsdesenvolvimento.organizecontas.utils.state.DataUserState
import com.google.firebase.auth.FirebaseAuth

class UserLoggedDS(
    private val authDS: FirebaseAuth
) : AuthActions.UserLogged {
    override suspend fun logout() {
        return authDS.signOut()
    }

    override suspend fun userKey(): String {
        return authDS.currentUser?.uid ?: ERROR_ID
    }

    companion object {
        const val ERROR_ID = "Erro ao gerar Id"
    }
}