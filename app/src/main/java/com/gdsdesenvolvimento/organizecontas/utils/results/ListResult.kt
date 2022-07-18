package com.gdsdesenvolvimento.organizecontas.utils.results

import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm

sealed class ListResult {
    object Loading : ListResult()
    data class Success(val data: Pair<ArrayList<ItemCreditCardForm>, ArrayList<ItemAccountForm>>) :
        ListResult()

    data class Error(val msg: String) : ListResult()
}