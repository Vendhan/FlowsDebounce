package com.vendhan.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val query = MutableStateFlow("")

    init {
        viewModelScope.launch {
            query
                .debounce(timeoutMillis = 1500)
                .distinctUntilChanged()
                .collect {
                    // Do some DB operation
                    println("printing DB values $it")
                }
        }
    }
}
