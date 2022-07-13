package com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime

import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

interface DatabaseActions {
    interface DataUser {
        suspend fun saveDataRegister(user: UserRegister): Task<Void>
        suspend fun getDataUser(user: UserRegister): Task<DataSnapshot>
        suspend fun deleteAllDataUser(user: UserRegister): Task<Void>
    }

    interface Accounts {
        suspend fun saveConfigAccount(
            number: Int,
            idUserLogged: String,
            item: ItemAccountForm
        ): Task<Void>
        suspend fun getItemsAccount(position: String): Task<DataSnapshot>

    }

    interface CreditCard {
        suspend fun saveCreditCardConfig(
            numberPosition: Int,
            idUserLogged: String,
            item: ItemCreditCardForm
        ): Task<Void>
        suspend fun getItemsCreditCard(position : String): Task<DataSnapshot>
    }
}