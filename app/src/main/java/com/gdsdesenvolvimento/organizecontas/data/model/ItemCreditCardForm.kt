package com.gdsdesenvolvimento.organizecontas.data.model

data class ItemCreditCardForm(
    val nomeDoBanco : String,
    val limiteDoCartao : Double,
    val isLimitUsage : Boolean,
    val valueLimitUsage : Double
)
