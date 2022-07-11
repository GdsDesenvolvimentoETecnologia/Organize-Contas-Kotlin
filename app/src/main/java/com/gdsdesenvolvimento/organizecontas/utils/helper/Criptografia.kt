package com.gdsdesenvolvimento.organizecontas.utils.helper

import android.util.Base64

object Criptografia {

    fun encodePassword(password: String): String{
        return Base64.encodeToString(password.toByteArray(), Base64.DEFAULT)
            .replace("(\\n\\r)".toRegex(), "")
    }
    fun decodePassword(password: String): String {
        return String(Base64.decode(password, Base64.DEFAULT))
    }
}