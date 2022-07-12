package com.gdsdesenvolvimento.organizecontas.data.model

data class ItemAccountForm(
    var nomeDoBanco : String,
    var valorNaConta : Double,
    var possuiLimite : Boolean,
    var valorLimite : Double?
)
