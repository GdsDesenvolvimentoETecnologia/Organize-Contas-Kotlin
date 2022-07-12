package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository
import com.gdsdesenvolvimento.organizecontas.databinding.RvItemAccountBinding
import com.gdsdesenvolvimento.organizecontas.utils.extensions.*
import com.gdsdesenvolvimento.organizecontas.utils.helper.ValidateAdapterForm.ckIsChecked
import com.gdsdesenvolvimento.organizecontas.utils.helper.ValidateAdapterForm.validateBankName
import com.gdsdesenvolvimento.organizecontas.utils.helper.ValidateAdapterForm.verifyNumberValue
import com.gdsdesenvolvimento.organizecontas.utils.results.FinishItemResult
import com.gdsdesenvolvimento.organizecontas.utils.results.SaveFormResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConfigAccountAdapter constructor(
    private var numbersForms: Int,
    private val idUserLogged: String,
    private val realtimeRepository: RealtimeRepository,
    private val callback: FinishItemResult,
    private val resultSaveForm : SaveFormResult
) : RecyclerView.Adapter<ConfigAccountAdapter.ConfigAccountViewHolder>() {

    private lateinit var item: ItemAccountForm

    inner class ConfigAccountViewHolder(val binding: RvItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigAccountViewHolder {
        return ConfigAccountViewHolder(
            RvItemAccountBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = numbersForms
    override fun onBindViewHolder(holder: ConfigAccountViewHolder, position: Int) {
        holder.apply {
            bind(
                position,
                this.binding.nomeBanco,
                this.binding.ckLimite,
                this.binding.valorConta,
                this.binding.valorLimite,
                this.binding.btnSalvarForm
            )
        }
    }

    private fun bind(
        position: Int,
        nomeBanco: EditText,
        ckLimite: CheckBox,
        valorConta: EditText,
        valorLimite: EditText,
        btnSalvarForm: Button
    ) {
        nomeBanco.afterTextChanged { safeName ->
            validateBankName(btnSalvarForm, nomeBanco, safeName)
        }
        ckLimite.setOnClickListener {
            ckIsChecked(ckLimite, btnSalvarForm, valorLimite)
        }
        valorConta.afterTextChanged { safeValorConta ->
            verifyNumberValue(btnSalvarForm, valorConta, safeValorConta)
        }
        valorLimite.afterTextChanged { safeValorLimite ->
            verifyNumberValue(btnSalvarForm, valorLimite, safeValorLimite)
        }
        btnSalvarForm.setOnClickListener {
            verifyQtdForms(nomeBanco, valorConta, ckLimite, valorLimite, position)
        }

    }

    private fun verifyQtdForms(
        nomeBanco: EditText,
        valorConta: EditText,
        ckLimite: CheckBox,
        valorLimite: EditText,
        position: Int
    ) {
        saveDataForm(
            nomeBanco.extractString(),
            valorConta.extractDouble(),
            ckLimite.isChecked,
            valorLimite.extractDouble(),
            position
        )
        if (numbersForms == 0) {
            callback.finish()
        }
    }

    private fun saveDataForm(
        nomeBanco: String,
        valorConta: Double,
        ckLimite: Boolean,
        valorLimite: Double,
        position: Int
    ) {
        item = DI.getFormAccount(nomeBanco, valorConta, ckLimite, valorLimite)
        saveFirebase(position)
        numbersForms -= 1
        notifyItemRemoved(0)
    }

    private fun saveFirebase(position: Int) {
        GlobalScope.launch {
            realtimeRepository.saveAccountsConfig(position, idUserLogged, item)
                .addOnSuccessListener {
                    resultSaveForm.success()
                }
                .addOnFailureListener {
                    resultSaveForm.failed(it.message!!)
                }
        }
    }
}
