package com.gdsdesenvolvimento.organizecontas.data.di

import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigAccountAdapter
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigCreditCardAdapter
import com.gdsdesenvolvimento.organizecontas.ui.adapter.MainAdapterAccount
import com.gdsdesenvolvimento.organizecontas.ui.adapter.MainAdapterCreditCard
import com.gdsdesenvolvimento.organizecontas.utils.results.FinishItemResult
import com.gdsdesenvolvimento.organizecontas.utils.results.SaveFormResult

object AdapterInjection {
    fun getAdapterMainAccount(list : List<ItemAccountForm>): MainAdapterAccount {
        return MainAdapterAccount(list)
    }
    fun getAdapterMainCreditCard(list : List<ItemCreditCardForm>): MainAdapterCreditCard {
        return MainAdapterCreditCard(list)
    }
    fun creditCardAdapter(
        qtdForms: Int,
        finishItemResult: FinishItemResult,
        result: SaveFormResult
    ): ConfigCreditCardAdapter {
        return ConfigCreditCardAdapter(
            qtdForms,
            DI.userKey(),
            DI.getRealtimeRepository(),
            finishItemResult,
            result
        )
    }

    fun accountAdapter(
        qtdForms: Int,
        finishItemResult: FinishItemResult,
        result: SaveFormResult

    ): ConfigAccountAdapter {
        return ConfigAccountAdapter(
            qtdForms,
            DI.userKey(),
            DI.getRealtimeRepository(),
            finishItemResult,
            result
        )
    }
}