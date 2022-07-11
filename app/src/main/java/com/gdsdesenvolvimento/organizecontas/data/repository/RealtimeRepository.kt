package com.gdsdesenvolvimento.organizecontas.data.repository

import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.Accounts
import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.DataUser
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountConfig
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister

class RealtimeRepository(
    private val dataUser: DataUser,
    private val account : Accounts
) {
    suspend fun saveDataUser(user: UserRegister) = dataUser.saveDataRegister(user)
    suspend fun getDataUser(user: UserRegister) = dataUser.getDataUser(user)
    suspend fun deleteAllDataUser(user: UserRegister) = dataUser.deleteAllDataUser(user)
    suspend fun saveAccountsConfig(numberPosition : Int,idUserLogged: String,item: ItemAccountConfig) = account.saveConfigAccount(numberPosition,idUserLogged, item)
}