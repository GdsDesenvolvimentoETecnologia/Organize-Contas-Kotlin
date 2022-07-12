package com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime

import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

class CreditCard(private val db: DatabaseReference) : DatabaseActions.CreditCard {
    override fun saveCreditCardConfig(
        numberPosition: Int,
        idUserLogged: String,
        item: ItemCreditCardForm
    ): Task<Void> {
        return db.child(DBConstants.USUARIOS)
            .child(idUserLogged)
            .child(DBConstants.CREDIT_CARD_CONFIG)
            .child(numberPosition.toString())
            .setValue(item)
    }
}