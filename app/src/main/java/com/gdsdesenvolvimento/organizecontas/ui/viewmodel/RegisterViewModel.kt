package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.*
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.utils.helper.FormValidation
import com.gdsdesenvolvimento.organizecontas.utils.state.RegisterFormState
import com.gdsdesenvolvimento.organizecontas.utils.results.FormResult
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepo: AuthenticatorRepository) : ViewModel() {

    private var _registerFormState = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> get() = _registerFormState

    private var _formResult = MutableLiveData<FormResult>()
    val formResult: LiveData<FormResult> get() = _formResult


    fun registerEmailWithPassword(email: String, password: String) = viewModelScope.launch {
        _formResult.value = FormResult.Loading
        authRepo.registerEmailWithPass(email, password)
            .addOnSuccessListener { successResult ->
                _formResult.value = FormResult.Success(successResult)
            }
            .addOnFailureListener {
                val msg = try {
                    throw it
                } catch (e: FirebaseAuthWeakPasswordException) {
                    e.message
                }catch (e : FirebaseAuthInvalidCredentialsException){
                    e.message
                }catch (e : FirebaseAuthUserCollisionException){
                    e.message + "Usuario ja existe"
                }catch (e : FirebaseException){
                    e.message
                }
                _formResult.value = FormResult.Error(msg)
            }
    }

    fun formRegisterDataChange(
        nome: String,
        email: String,
        senha: String,
        confirmSenha: String,
        phone: String
    ) {
        verifyStringNotEmpty(nome, email, senha, confirmSenha, phone)
        verifyTypeText(nome, email, senha, confirmSenha, phone)
    }

    private fun verifyStringNotEmpty(
        nome: String,
        email: String,
        senha: String,
        confirmSenha: String,
        phone: String
    ) {
        if (nome.isEmpty()) {
            _registerFormState.value = RegisterFormState.ErrorEmpty.ErrorNameEmpty("nome em branco")
        }
        if (email.isEmpty()) {
            _registerFormState.value =
                RegisterFormState.ErrorEmpty.ErrorEmailEmpty("email em branco ")
        }
        if (senha.isEmpty()) {
            _registerFormState.value =
                RegisterFormState.ErrorEmpty.ErrorPasswordEmpty("Senha em branco ")
        }
        if (confirmSenha.isEmpty()) {
            _registerFormState.value =
                RegisterFormState.ErrorEmpty.ErrorConfirmPasswordEmpty("confirma senha em branco")
        }
        if (phone.isEmpty()) {
            _registerFormState.value =
                RegisterFormState.ErrorEmpty.ErrorPhoneEmpty("telefone em branco")
        }
    }

    private fun verifyTypeText(
        nome: String,
        email: String,
        senha: String,
        confirmSenha: String,
        phone: String
    ) {
        if (!FormValidation.isNameValid(nome)) {
            _registerFormState.value =
                RegisterFormState.ErrorQtdCharactersInvalid("Quantidade de caracteres invalido")
        } else if (!FormValidation.isUserNameValid(email)) {
            _registerFormState.value =
                RegisterFormState.ErrorEmailNotValid("Formato de email invalido")
        } else if (!FormValidation.isPasswordValid(senha)) {
            _registerFormState.value =
                RegisterFormState.ErrorPasswordInvalid("Formato de senha invalido necessario no minimo 7 letras")
        } else if (senha != confirmSenha) {
            _registerFormState.value =
                RegisterFormState.ErrorPasswordNotEquals("Diferente do campo senha")
        } else if (!FormValidation.isPhoneValid(phone)) {
            _registerFormState.value =
                RegisterFormState.ErrorPhoneInvalid("Formato de telefone invalido")
        } else {
            val user = UserRegister(nome, email, senha, phone)
            _registerFormState.value = RegisterFormState.Success(user)

        }
    }

    fun saveRegisterDataUser() {

    }
}
