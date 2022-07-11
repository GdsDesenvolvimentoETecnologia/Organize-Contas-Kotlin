package com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime

import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

interface DatabaseActions {
    interface DataUser {
        suspend fun saveDataRegister(user: UserRegister): Task<Void>
        suspend fun getDataUser(user: UserRegister): Task<DataSnapshot>
        suspend fun deleteAllDataUser(user: UserRegister): Task<Void>
    }
}