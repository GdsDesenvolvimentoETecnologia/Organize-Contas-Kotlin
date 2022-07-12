package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository
import com.gdsdesenvolvimento.organizecontas.databinding.RvItemCreditCardBinding
import com.gdsdesenvolvimento.organizecontas.utils.extensions.afterTextChanged
import com.gdsdesenvolvimento.organizecontas.utils.extensions.extractDouble
import com.gdsdesenvolvimento.organizecontas.utils.extensions.extractString
import com.gdsdesenvolvimento.organizecontas.utils.helper.ValidateAdapterForm.ckIsChecked
import com.gdsdesenvolvimento.organizecontas.utils.helper.ValidateAdapterForm.validateBankName
import com.gdsdesenvolvimento.organizecontas.utils.helper.ValidateAdapterForm.verifyNumberValue
import com.gdsdesenvolvimento.organizecontas.utils.results.FinishItemResult
import com.gdsdesenvolvimento.organizecontas.utils.results.SaveFormResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConfigCreditCardAdapter(
    private var numbersForms: Int,
    private val idUserLogged: String,
    private val realtimeRepository: RealtimeRepository,
    private val callback: FinishItemResult,
    private val resultSaveForm : SaveFormResult
) : RecyclerView.Adapter<ConfigCreditCardAdapter.ConfigCreditCardViewHolder>() {
    private lateinit var item: ItemCreditCardForm

    inner class ConfigCreditCardViewHolder(val binding: RvItemCreditCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigCreditCardViewHolder {
        return ConfigCreditCardViewHolder(
            RvItemCreditCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = numbersForms

    override fun onBindViewHolder(holder: ConfigCreditCardViewHolder, position: Int) {
        holder.apply {
            bind(
                position,
                this.binding.bankNameCreditCard,
                this.binding.limitCreditCard,
                this.binding.ckCreditCard,
                this.binding.valorJaUtilizado,
                this.binding.btnSalvarFormulario

            )
        }
    }

    private fun bind(
        position: Int,
        bankNameCreditCard: EditText,
        limitCreditCard: EditText,
        ckCreditCard: CheckBox,
        valorJaUtilizado: EditText,
        btnSalvarFormulario: Button
    ) {
        bankNameCreditCard.afterTextChanged { bankName ->
            validateBankName(btnSalvarFormulario, bankNameCreditCard, bankName)
        }
        limitCreditCard.afterTextChanged { limitCard ->
            verifyNumberValue(btnSalvarFormulario, limitCreditCard, limitCard)
        }
        ckCreditCard.setOnClickListener {
            ckIsChecked(ckCreditCard, btnSalvarFormulario, valorJaUtilizado)
        }
        valorJaUtilizado.afterTextChanged { value ->
            verifyNumberValue(btnSalvarFormulario, valorJaUtilizado, value)
        }
        btnSalvarFormulario.setOnClickListener {
            verifyQtdForms(
                bankNameCreditCard,
                limitCreditCard,
                ckCreditCard,
                valorJaUtilizado,
                position
            )
        }
    }

    private fun verifyQtdForms(
        bankNameCreditCard: EditText,
        limitCreditCard: EditText,
        ckCreditCard: CheckBox,
        valorJaUtilizado: EditText,
        position: Int
    ) {
        saveDataForm(
            bankNameCreditCard.extractString(),
            limitCreditCard.extractDouble(),
            ckCreditCard.isChecked,
            valorJaUtilizado.extractDouble(),
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
        item = DI.getFormCreditCard(nomeBanco, valorConta, ckLimite, valorLimite)
        saveFirebase(position)
        numbersForms -= 1
        notifyItemRemoved(0)
    }

    private fun saveFirebase(position: Int) {
        GlobalScope.launch {
            realtimeRepository.saveCreditCardConfig(position, idUserLogged, item)
                .addOnSuccessListener {
                    resultSaveForm.success()
                }
                .addOnFailureListener {
                    resultSaveForm.failed(it.message!!)
                }
        }
    }
}