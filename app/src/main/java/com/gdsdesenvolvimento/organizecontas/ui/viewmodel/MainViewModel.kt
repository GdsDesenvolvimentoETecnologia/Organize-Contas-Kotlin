package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository
import com.gdsdesenvolvimento.organizecontas.utils.results.ListResult
import com.google.firebase.database.DatabaseException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val realtimeRepository: RealtimeRepository,
) : ViewModel() {
    private val lists: Pair<ArrayList<ItemCreditCardForm>, ArrayList<ItemAccountForm>> =
        Pair(arrayListOf(), arrayListOf())

    private var _listsResultAccount = MutableLiveData<ListResult>()
    val listsResultAccount: LiveData<ListResult> get() = _listsResultAccount

    private var _listsResultCreditCard = MutableLiveData<ListResult>()
    val listsResultCreditCard: LiveData<ListResult> get() = _listsResultCreditCard

    fun fetchLists(
        qtdAccount: Int,
        qtdCreditCard: Int
    ){
        GlobalScope.launch {
            getCreditCard(qtdCreditCard)
            getAccountList(qtdAccount)
        }
    }

    private suspend fun getAccountList(qtdAccount: Int) {
        for (position in 0..qtdAccount) {
            realtimeRepository.listAccount(position.toString())
                .addOnSuccessListener { snapshotAccount ->
                    val itemAccount = snapshotAccount.getValue(ItemAccountForm::class.java)
                    itemAccount?.let { safeItemAccount ->
                        lists.second.add(safeItemAccount)
                    }
                    _listsResultAccount.value = ListResult.Success(lists)
                }
                .addOnFailureListener { exception ->
                    val msg = try {
                        exception
                    } catch (e: DatabaseException) {
                        e.message
                    }
                    _listsResultAccount.value = ListResult.Error(msg.toString())
                }
        }
    }

    private suspend fun getCreditCard(qtdCreditCard: Int) {
        for (position in 0..qtdCreditCard) {
            realtimeRepository.listCreditCard(position.toString())
                .addOnSuccessListener { snapshotCreditCard ->
                    val itemCard = snapshotCreditCard.getValue(ItemCreditCardForm::class.java)
                    itemCard?.let { safeItemCard ->
                        lists.first.add(safeItemCard)
                    }
                    _listsResultCreditCard.value = ListResult.Success(lists)
                }
                .addOnFailureListener { exception ->
                    val msg = try {
                        exception
                    } catch (e: DatabaseException) {
                        e.message
                    }
                    _listsResultCreditCard.value = ListResult.Error(msg.toString())
                }
        }
    }
}