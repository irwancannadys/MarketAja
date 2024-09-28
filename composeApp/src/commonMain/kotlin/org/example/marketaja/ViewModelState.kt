package org.example.marketaja

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class ViewModelState<STATE, ACTION>(
    private val initiate: STATE
): ViewModel() {

    private val _stateflow = MutableStateFlow(initiate)
    val state : StateFlow<STATE> get() = _stateflow

    abstract fun handleAction(action: ACTION)

    protected fun update(block : STATE.() -> STATE){
        _stateflow.update(block)
    }

    fun currentState() {
        state.value
    }

    fun restartState() {
        _stateflow.update { initiate }
    }
}