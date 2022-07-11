package com.gdsdesenvolvimento.organizecontas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.whenResumed
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.di.ViewModelInjection
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityRegisterBinding
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.RegisterViewModel
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.ViewModelFactory
import com.gdsdesenvolvimento.organizecontas.utils.extensions.*
import com.gdsdesenvolvimento.organizecontas.utils.results.FormResult
import com.gdsdesenvolvimento.organizecontas.utils.state.DataUserState
import com.gdsdesenvolvimento.organizecontas.utils.state.FormState
import com.gdsdesenvolvimento.organizecontas.utils.state.RegisterFormState

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var newUser: UserRegister
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActivity()
        observers()
    }

    private fun setupActivity() {
        viewModel = ViewModelInjection.getRegisterViewModel(this)
        initComponents()
    }

    private fun initComponents() = with(binding) {
        editName.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        editEmail.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        editPassword.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        editConfirmPassword.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        editPhone.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        button.setOnClickListener {
            viewModel.registerEmailWithPassword(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            )
        }
    }

    private fun observers() {
        val nome = binding.editName
        val email = binding.editEmail
        val senha = binding.editPassword
        val confirmSenha = binding.editConfirmPassword
        val phone = binding.editPhone
        viewModel.registerFormState.observe(this) { result ->
            when (result) {
                is RegisterFormState.ErrorEmpty.ErrorNameEmpty -> {
                    setEditError(nome, result.msg)
                }
                is RegisterFormState.ErrorEmpty.ErrorEmailEmpty -> {
                    setEditError(email, result.msg)
                }
                is RegisterFormState.ErrorEmpty.ErrorPasswordEmpty -> {
                    setEditError(senha, result.msg)
                }
                is RegisterFormState.ErrorEmpty.ErrorConfirmPasswordEmpty -> {
                    setEditError(confirmSenha, result.msg)
                }
                is RegisterFormState.ErrorEmpty.ErrorPhoneEmpty -> {
                    setEditError(phone, result.msg)
                }
                is RegisterFormState.ErrorQtdCharactersInvalid -> {
                    setEditError(nome, result.msg)
                }
                is RegisterFormState.ErrorEmailNotValid -> {
                    setEditError(email, result.msg)
                }
                is RegisterFormState.ErrorPasswordInvalid -> {
                    setEditError(senha, result.msg)
                }
                is RegisterFormState.ErrorPasswordNotEquals -> {
                    setEditError(confirmSenha, result.msg)
                }
                is RegisterFormState.ErrorPhoneInvalid -> {
                    setEditError(phone, result.msg)
                }
                is RegisterFormState.Success -> {
                    newUser = DI.getUseRegister(
                        result.data.nome,
                        result.data.email,
                        result.data.password,
                        result.data.phone
                    )
                    binding.button.isEnabled = true
                }
            }
        }
        viewModel.formResult.observe(this) { result ->
            when (result) {
                is FormResult.Success -> {
                    viewModel.saveDataUserRegister(newUser)
                }
                is FormResult.Error -> {
                    dialog(
                        this,
                        getString(R.string.falha),
                        "Formulario com erro ou campo em branco",
                        true
                    ) {
                        finish()
                    }
                }
                is FormResult.Loading -> {
                    message(getString(R.string.carregando))
                }
            }
        }
        viewModel.saveData.observe(this) { resultSaveData ->
            when (resultSaveData) {
                is DataUserState.Success -> {
                    dialog(
                        this,
                        getString(R.string.bem_vindo_novo_integrante),
                        getString(R.string.msg_boas_vindas),
                        true
                    ) {
                        nextScreen(ConfigurationAppActivity())
                    }
                }
                is DataUserState.Error -> {
                    dialog(
                        this,
                        getString(R.string.falha),
                        "Falha ao salvar os dados ${resultSaveData.msg}",
                        true
                    ) {
                        finish()
                    }
                }
                is DataUserState.Loading -> {
                    message("Continua carregando agora pra salvar")
                }
            }
        }
    }
}