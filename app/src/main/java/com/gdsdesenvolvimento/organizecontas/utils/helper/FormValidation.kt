package com.gdsdesenvolvimento.organizecontas.utils.helper

import android.util.Patterns

object FormValidation {
    // A placeholder username validation check
    fun isUserNameValid(username: String): Boolean {
        if (username.contains("@") && username.contains(".com")){
            return true
        }
        return false
    }

    // A placeholder password validation check
    fun isPasswordValid(password: String): Boolean {
        return password.length > 6
    }

    fun isPhoneValid(phone: String): Boolean {
        if (phone.length < 11 || phone.length >11) {
            return false
        }
        return true
    }

    fun isNameValid(nome: String): Boolean {
        return nome.length > 2
    }

}