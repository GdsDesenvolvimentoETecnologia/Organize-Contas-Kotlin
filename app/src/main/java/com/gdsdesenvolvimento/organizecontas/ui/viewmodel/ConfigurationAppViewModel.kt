package com.gdsdesenvolvimento.organizecontas.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfigurationAppViewModel : ViewModel() {
    private var _settings = MutableLiveData<Boolean>()
    val settings: LiveData<Boolean> get() = _settings

    fun numberIsValid(number: Int) {
        _settings.value = number != 0
    }

}