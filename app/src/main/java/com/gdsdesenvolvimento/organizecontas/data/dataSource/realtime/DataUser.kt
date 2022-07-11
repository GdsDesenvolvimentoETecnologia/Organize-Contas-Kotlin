package com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime

import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference

class DataUser(private val realtime: DatabaseReference) : DatabaseActions.DataUser {
    override suspend fun saveDataRegister(user: UserRegister): Task<Void> {
        return realtime.child(DBConstants.USUARIOS)
            .child(user.id!!)
            .child(DBConstants.DADOS_PESSOAIS)
            .setValue(user)
    }
    override suspend fun getDataUser(user: UserRegister): Task<DataSnapshot> {
        return realtime.child(DBConstants.USUARIOS)
            .child(user.id!!)
            .child(DBConstants.DADOS_PESSOAIS)
            .get()
    }
    override suspend fun deleteAllDataUser(user: UserRegister): Task<Void> {
        return realtime.child(DBConstants.USUARIOS)
            .child(user.id!!)
            .child(DBConstants.DADOS_PESSOAIS)
            .removeValue()
    }
}