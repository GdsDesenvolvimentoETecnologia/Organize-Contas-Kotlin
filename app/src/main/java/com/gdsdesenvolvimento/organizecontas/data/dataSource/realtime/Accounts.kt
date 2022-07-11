package com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime

import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountConfig
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

class Accounts(private val db : DatabaseReference) : DatabaseActions.Accounts {
    override suspend fun saveConfigAccount(number : Int,idUserLogged: String, item : ItemAccountConfig): Task<Void> {
        return db.child(DBConstants.USUARIOS)
            .child(idUserLogged)
            .child(DBConstants.ACCOUNT_CONFIG)
            .child(number.toString())
            .setValue(item)
    }
}