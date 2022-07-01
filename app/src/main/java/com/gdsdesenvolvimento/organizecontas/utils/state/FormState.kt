package com.gdsdesenvolvimento.organizecontas.utils.state

import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

sealed class FormState {
    object Empty : FormState()
    object Loading : FormState()
    data class Error(val msg : String) : FormState()
    data class Success(val data : Any?) : FormState()
}