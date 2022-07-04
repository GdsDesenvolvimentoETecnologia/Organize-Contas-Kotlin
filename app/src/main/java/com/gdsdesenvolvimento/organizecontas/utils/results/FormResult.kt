package com.gdsdesenvolvimento.organizecontas.utils.results

sealed class FormResult {
    object Loading : FormResult()
    data class Error(val msg : String?) : FormResult()
    data class Success(val data : Any) : FormResult()
}