package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.databinding.ItemPrincipalBinding

class MainAdapterCreditCard(
    private val listCreditCard: List<ItemCreditCardForm>
) : RecyclerView.Adapter<MainAdapterCreditCard.MainViewHolder>() {
    inner class MainViewHolder(val binding: ItemPrincipalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemPrincipalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listCreditCard.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val itemCreditCard = listCreditCard[position]
        holder.apply {
            bind(holder.binding,itemCreditCard,position)
        }
    }

    private fun bind(
        binding: ItemPrincipalBinding,
        itemCreditCardForm: ItemCreditCardForm,
        position: Int
    ) {

    }
}