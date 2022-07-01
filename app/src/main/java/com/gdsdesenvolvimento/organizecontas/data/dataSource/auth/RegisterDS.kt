package com.gdsdesenvolvimento.organizecontas.data.dataSource.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegisterDS(private val authDS : FirebaseAuth) : AuthActions.Register {
    override suspend fun registerUserEmailWithPassword(
        email: String,
        password: String
    ): Task<AuthResult> {
        return authDS.createUserWithEmailAndPassword(email, password)
    }
}