package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.databinding.ItemPrincipalBinding

class MainAdapterAccount(
    private val listAccount: ArrayList<ItemAccountForm>
) : RecyclerView.Adapter<MainAdapterAccount.AccountViewHolder>() {
    inner class AccountViewHolder(val binding: ItemPrincipalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            ItemPrincipalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listAccount.size

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val itemAccountForm = listAccount[position]
        holder.apply {
            bind(this.binding, itemAccountForm)
        }
    }

    private fun bind(
        binding: ItemPrincipalBinding,
        itemAccountForm: ItemAccountForm
    ) {
        binding.bankName.text = itemAccountForm.nomeDoBanco
        if (itemAccountForm.possuiLimite) {
            binding.textValorTotal.text =
                valorRestante(itemAccountForm.valorNaConta, itemAccountForm.valorLimite!!)
        } else {
            binding.textValorTotal.text = itemAccountForm.valorNaConta.toString()
        }
    }

    private fun valorRestante(valorNaConta: Double, valorLimite: Double): CharSequence? {
        val result = valorNaConta + valorLimite
        return result.toString()
    }
}