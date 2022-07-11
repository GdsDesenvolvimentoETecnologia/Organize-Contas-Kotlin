package com.gdsdesenvolvimento.organizecontas.utils.state

sealed class DataUserState {
    object Loading : DataUserState()
    data class Success(val data: Any) : DataUserState()
    data class Error(val msg: String) : DataUserState()
}
