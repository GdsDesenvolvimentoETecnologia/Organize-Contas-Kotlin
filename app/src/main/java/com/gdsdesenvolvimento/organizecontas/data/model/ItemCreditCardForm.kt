package com.gdsdesenvolvimento.organizecontas.data.model

data class ItemCreditCardForm(

    val nomeDoBanco : String,
    val limiteDoCartao : Double,
    val isLimitUsage : Boolean,
    val valueLimitUsage : Double
){
    constructor():this("",0.00,false,0.00)
}
