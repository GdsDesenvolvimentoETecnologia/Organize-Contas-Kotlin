package com.gdsdesenvolvimento.organizecontas.data.dataSource.auth

import com.google.firebase.auth.FirebaseAuth

class UserLoggedDS(
    private val authDS: FirebaseAuth
) : AuthActions.UserLogged {
    override suspend fun logout() {
        return authDS.signOut()
    }
}