package com.gdsdesenvolvimento.organizecontas.data.dataSource.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthActions {

    interface Login {
        suspend fun loginUserEmailWithPassword(email: String, password: String): Task<AuthResult>
        suspend fun loginUserAnonymous(): Task<AuthResult>
    }

    interface Register {
        suspend fun registerUserEmailWithPassword(email: String, password: String): Task<AuthResult>
    }

    interface UserLogged {
        suspend fun logout()
    }
}