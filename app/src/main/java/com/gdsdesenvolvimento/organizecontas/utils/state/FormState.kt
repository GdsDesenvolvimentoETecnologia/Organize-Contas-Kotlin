package com.gdsdesenvolvimento.organizecontas.utils.state

import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

sealed class FormState {
    sealed class ErrorEmail : FormState(){
        data class ErrorEmpty(val msg: String) : ErrorEmail()
        data class ErrorFormatInvalid(val msg: String) : ErrorEmail()
    }
    sealed class ErrorPassword(){
        data class ErrorEmpty(val msg: String) : ErrorEmail()
        data class ErrorFormatInvalid(val msg: String) : ErrorEmail()
    }
    data class Success(val data : Any?) : FormState()
}