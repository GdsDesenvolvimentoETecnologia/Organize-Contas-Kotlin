package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsdesenvolvimento.organizecontas.data.dataSource.realtime.DBConstants
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.data.model.ItemCreditCardForm
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository
import com.gdsdesenvolvimento.organizecontas.utils.results.ListResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val realtimeRepository: RealtimeRepository,
) : ViewModel() {
    private val listsAccount: ArrayList<ItemAccountForm> = arrayListOf()
    private val listsCreditCard: ArrayList<ItemCreditCardForm> = arrayListOf()

    private var _listsResultAccount = MutableLiveData<ListResult<ItemAccountForm>>()
    val listsResultAccount: LiveData<ListResult<ItemAccountForm>> get() = _listsResultAccount

    private var _listsResultCreditCard = MutableLiveData<ListResult<ItemCreditCardForm>>()
    val listsResultCreditCard: LiveData<ListResult<ItemCreditCardForm>> get() = _listsResultCreditCard

    private var _listResults = MutableLiveData<Boolean>()
    val listResults : LiveData<Boolean> get() = _listResults
    init {
        initialized()
    }

    private fun initialized() = viewModelScope.launch{
        coroutineScope {
            fetchQtdAsync().await()
        }.addOnSuccessListener { result ->
            result.children.map { newIt ->
                when (newIt.key) {
                    DBConstants.ACCOUNT_CONFIG -> {
                        newIt.children.map { snapAccount ->
                            val account = snapAccount.getValue(ItemAccountForm::class.java)
                            account?.let { safeAccount ->
                                listsAccount.add(safeAccount)
                            }
                        }
                        if (listsAccount.isNotEmpty()) {
                            _listsResultAccount.value = ListResult.Success(listsAccount)
                        } else {
                            _listsResultAccount.value = ListResult.Error("Lista vazia")
                        }
                    }
                    DBConstants.CREDIT_CARD_CONFIG -> {
                        newIt.children.map { snapCreditCard ->
                            val creditCard = snapCreditCard.getValue(ItemCreditCardForm::class.java)
                            creditCard?.let { safeCreditCard -> listsCreditCard.add(safeCreditCard) }
                            Log.d("Fluxo", "initialized: ${creditCard}")
                        }
                        if (listsCreditCard.isNotEmpty()) {
                            _listsResultCreditCard.value = ListResult.Success(listsCreditCard)
                        } else {
                            _listsResultCreditCard.value = ListResult.Error("Lista Vazia")
                        }
                    }
                    else -> {}
                }
            }
            _listResults.value = listsCreditCard.isNotEmpty() && listsAccount.isNotEmpty()
        }.addOnFailureListener {
            it.message
        }
    }

    private fun fetchQtdAsync() = viewModelScope.async {
        _listsResultCreditCard.value = ListResult.Loading()
        _listsResultAccount.value = ListResult.Loading()
        realtimeRepository.qtdItemsList()
    }

}