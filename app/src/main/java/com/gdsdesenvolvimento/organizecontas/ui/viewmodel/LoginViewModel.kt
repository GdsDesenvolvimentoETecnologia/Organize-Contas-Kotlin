package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.model.User
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.utils.helper.FormValidation
import com.gdsdesenvolvimento.organizecontas.utils.state.FormState
import com.gdsdesenvolvimento.organizecontas.utils.results.FormResult
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch

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
            val user = DI.getUseLogin(username, password)
            _loginFormState.value = FormState.Success(user)
        }
    }

    fun loginEmailWithPassword(user: User) = viewModelScope.launch {
        _userResult.value = FormResult.Loading
        authRepo.loginEmailWithPass(user.email, user.password)
            .addOnSuccessListener { successResult ->
                _userResult.value = FormResult.Success(successResult)
            }
            .addOnFailureListener {
                val msg = try {
                    throw it
                } catch (e: FirebaseAuthInvalidCredentialsException ) {
                   "Senha incorreta " + e.message
                }catch (e : FirebaseAuthInvalidUserException){
                    "nao cadastrado" + e.message
                }catch (e : FirebaseAuthException){
                    e.message
                }
                _userResult.value = FormResult.Error(msg)
            }
    }

}