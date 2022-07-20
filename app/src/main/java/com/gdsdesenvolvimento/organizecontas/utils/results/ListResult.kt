package com.gdsdesenvolvimento.organizecontas.utils.results

import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm

sealed class ListResult<T> {
    class Loading<T> : ListResult<T>()
    data class Success<T>(val data: ArrayList<T>) :
        ListResult<T>()

    data class Error<T>(val msg: String) : ListResult<T>()
}