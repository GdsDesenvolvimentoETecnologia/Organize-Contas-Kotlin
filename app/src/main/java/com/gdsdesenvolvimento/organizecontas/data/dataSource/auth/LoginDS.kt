package com.gdsdesenvolvimento.organizecontas.data.dataSource.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginDS(private val authDS : FirebaseAuth) : AuthActions.Login {
    override suspend fun loginUserEmailWithPassword(
        email: String,
        password: String
    ): Task<AuthResult> {
        return authDS.signInWithEmailAndPassword(email, password)
    }

    override suspend fun loginUserAnonymous(): Task<AuthResult> {
        return authDS.signInAnonymously()
    }
}