package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.utils.helper.FormValidation
import com.gdsdesenvolvimento.organizecontas.utils.state.FormState
import com.gdsdesenvolvimento.organizecontas.utils.results.FormResult

class LoginViewModel(private val authRepo: AuthenticatorRepository) : ViewModel() {

    private var _loginFormState = MutableLiveData<FormState>()
    val loginFormState: LiveData<FormState> get() = _loginFormState

    private var _userResult = MutableLiveData<FormResult>()
    val userResult: LiveData<FormResult> get() = _userResult

    fun formLoginDataChanged(username: String, password: String) {
        if (username.isEmpty()) {
            _loginFormState.value = FormState.ErrorEmail.ErrorEmpty("Campo email em branco")
        } else if (password.isEmpty()) {
            _loginFormState.value = FormState.ErrorPassword.ErrorEmpty("Campo senha em branco")
        } else if (!FormValidation.isUserNameValid(username)) {
            _loginFormState.value = FormState.ErrorEmail.ErrorFormatInvalid("email invalido")
        } else if (!FormValidation.isPasswordValid(password)) {
            _loginFormState.value = FormState.ErrorPassword.ErrorFormatInvalid("senha invalido")
        } else {
            _loginFormState.value = FormState.Success("Dados validos")
        }
    }

}