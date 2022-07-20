package com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime

import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class Accounts(private val db : DatabaseReference) : DatabaseActions.Accounts {
    override suspend fun saveConfigAccount(number : Int,idUserLogged: String, item : ItemAccountForm): Task<Void> {
        return db.child(DBConstants.USUARIOS)
            .child(idUserLogged)
            .child(DBConstants.ACCOUNT_CONFIG)
            .child(number.toString())
            .setValue(item)
    }

    override suspend fun getItemsAccount(position: String): Task<DataSnapshot> {
        return db.child(DBConstants.USUARIOS).child(DI.userKey()).child(DBConstants.ACCOUNT_CONFIG).child(position).get()
    }
    override suspend fun qtdItemsLit(): Task<DataSnapshot> {
        return db.child(DBConstants.USUARIOS).child(DI.userKey()).get()
    }
}