package com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime

import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class CreditCard(private val db: DatabaseReference) : DatabaseActions.CreditCard {
    override suspend fun saveCreditCardConfig(
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

    override suspend fun getItemsCreditCard(position : String): Task<DataSnapshot> {
        return db.child(DBConstants.USUARIOS).child(DI.userKey()).child(DBConstants.CREDIT_CARD_CONFIG).child(position).get()
    }

}