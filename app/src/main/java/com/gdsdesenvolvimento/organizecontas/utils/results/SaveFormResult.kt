package com.gdsdesenvolvimento.organizecontas.utils.results

interface SaveFormResult {
    fun success()
    fun failed(message: String)
}