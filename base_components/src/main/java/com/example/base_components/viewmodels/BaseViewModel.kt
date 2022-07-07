package com.example.base_components.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private var _error = MutableSharedFlow<Throwable>()
    val error = _error.asSharedFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    protected fun launch(block: suspend () -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            block.invoke()
            _isLoading.value = false
        }
    }

}
