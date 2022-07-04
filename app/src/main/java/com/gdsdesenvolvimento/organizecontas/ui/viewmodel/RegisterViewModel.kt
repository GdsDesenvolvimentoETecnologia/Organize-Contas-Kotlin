package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.*
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.utils.helper.FormValidation
import com.gdsdesenvolvimento.organizecontas.utils.state.RegisterFormState
import com.gdsdesenvolvimento.organizecontas.utils.results.FormResult
import com.google.firebase.FirebaseException
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepo: AuthenticatorRepository) : ViewModel() {
    private lateinit var user: UserRegister

    private var _registerFormState = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> get() = _registerFormState

    private var _isDataValid = MutableLiveData<Boolean>()
    val isDataValid: LiveData<Boolean> get() = _isDataValid

    private var _formResult = MutableLiveData<FormResult>()
    val formResult: LiveData<FormResult> get() = _formResult

    init {
        _isDataValid.value
    }

    fun registerEmailWithPassword(email: String, password: String) = viewModelScope.launch {
        _formResult.value = FormResult.Loading
        authRepo.registerEmailWithPass(email, password)
            .addOnSuccessListener { successResult ->
                _formResult.value = FormResult.Success(successResult)
            }
            .addOnFailureListener {
                val msg = try {
                    throw it
                } catch (e: FirebaseException) {
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
            _registerFormState.value = RegisterFormState.Success("Formulario correto")

        }
    }

    private fun verifyStringNotEmpty(
        nome: String,
        email: String,
        senha: String,
        confirmSenha: String,
        phone: String
    ) {
        if (nome.isEmpty()) {
            _registerFormState.value = RegisterFormState.ErrorEmpty.ErrorNameEmpty("dfghjklç")
        }
        if (email.isEmpty()) {
            _registerFormState.value = RegisterFormState.ErrorEmpty.ErrorEmailEmpty("dfghjklç")
        }
        if (senha.isEmpty()) {
            _registerFormState.value = RegisterFormState.ErrorEmpty.ErrorPasswordEmpty("dfghjklç")
        }
        if (confirmSenha.isEmpty()) {
            _registerFormState.value =
                RegisterFormState.ErrorEmpty.ErrorConfirmPasswordEmpty("dfghjklç")
        }
        if (phone.isEmpty()) {
            _registerFormState.value = RegisterFormState.ErrorEmpty.ErrorPhoneEmpty("dfghjklç")
        }
    }
}
