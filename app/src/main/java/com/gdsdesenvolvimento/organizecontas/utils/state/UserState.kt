package com.gdsdesenvolvimento.organizecontas.utils.state

sealed class UserState {
    data class Error(val msg : String) : UserState()
    data class Success(val data : String) : UserState()
}