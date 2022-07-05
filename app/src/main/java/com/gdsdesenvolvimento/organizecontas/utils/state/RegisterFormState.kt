package com.gdsdesenvolvimento.organizecontas.utils.state

import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister

sealed class RegisterFormState{
    data class  ErrorQtdCharactersInvalid(val msg : String) : RegisterFormState()
    data class  ErrorEmailNotValid(val msg : String) : RegisterFormState()
    data class  ErrorPasswordInvalid(val msg : String) : RegisterFormState()
    data class  ErrorPasswordNotEquals(val msg : String) : RegisterFormState()
    data class  ErrorPhoneInvalid(val msg : String) : RegisterFormState()
    data class  Success(val data : UserRegister) : RegisterFormState()

    sealed class ErrorEmpty(val string: String) : RegisterFormState() {
        data class  ErrorNameEmpty(val msg : String) : ErrorEmpty(msg)
        data class  ErrorEmailEmpty(val msg : String) : ErrorEmpty(msg)
        data class  ErrorPasswordEmpty(val msg : String) : ErrorEmpty(msg)
        data class  ErrorConfirmPasswordEmpty(val msg : String) : ErrorEmpty(msg)
        data class  ErrorPhoneEmpty(val msg : String) : ErrorEmpty(msg)
    }
}
