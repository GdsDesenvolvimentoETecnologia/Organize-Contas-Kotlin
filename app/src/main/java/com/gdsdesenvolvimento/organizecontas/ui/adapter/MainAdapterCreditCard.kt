package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.databinding.ItemPrincipalBinding

class MainAdapterCreditCard(
    private val listCreditCard: ArrayList<ItemCreditCardForm>
) : RecyclerView.Adapter<MainAdapterCreditCard.CreditCardViewHolder>() {

    inner class CreditCardViewHolder(val binding: ItemPrincipalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditCardViewHolder {
        return CreditCardViewHolder(
            ItemPrincipalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return listCreditCard.size
    }

    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) {
        val itemCreditCard = listCreditCard[position]
        holder.apply {
            bind(this.binding, itemCreditCard)
        }
    }

    private fun bind(
        binding: ItemPrincipalBinding,
        itemCreditCardForm: ItemCreditCardForm,
    ) {
        binding.bankName.text = itemCreditCardForm.nomeDoBanco
        if (itemCreditCardForm.isLimitUsage) {
            binding.textValorTotal.text =
                (itemCreditCardForm.limiteDoCartao - itemCreditCardForm.valueLimitUsage).toString()
        } else {
            binding.textValorTotal.text = itemCreditCardForm.limiteDoCartao.toString()
        }
    }
}