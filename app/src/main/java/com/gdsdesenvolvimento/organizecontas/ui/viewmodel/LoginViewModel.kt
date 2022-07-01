package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.utils.state.FormState
import com.gdsdesenvolvimento.organizecontas.utils.state.UserState

class LoginViewModel(val authRepo : AuthenticatorRepository) : ViewModel() {

    private var _loginFormState = MutableLiveData<FormState>()
    private val loginFormState : LiveData<FormState> get() = _loginFormState

    private var _userState = MutableLiveData<UserState>()
    private val userState : LiveData<UserState> get() = _userState

}