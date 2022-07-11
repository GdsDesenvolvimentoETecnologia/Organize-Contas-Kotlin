package com.gdsdesenvolvimento.organizecontas.data.repository

import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.DataUser
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister

class RealtimeRepository(
    private val dataUser: DataUser
) {
    suspend fun saveDataUser(user: UserRegister) = dataUser.saveDataRegister(user)
    suspend fun getDataUser(user: UserRegister) = dataUser.getDataUser(user)
    suspend fun deleteAllDataUser(user: UserRegister) = dataUser.deleteAllDataUser(user)
}