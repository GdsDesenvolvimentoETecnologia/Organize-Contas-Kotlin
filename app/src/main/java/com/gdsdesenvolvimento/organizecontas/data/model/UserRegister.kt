package com.gdsdesenvolvimento.organizecontas.data.model

data class UserRegister(
    var id : String? = null,
    var nome : String,
    override var email :String,
    override var password : String,
    var phone : String
) : User
