package com.example.calendar.ui.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

sealed class StateViewState {
    object NoSave : StateViewState()

    object InstantSave : StateViewState()

    object ViewModelSave : StateViewState()

    object ViewModelWithStateSave : StateViewState()
}

class StateViewModel(private val savedState: SavedStateHandle) : ViewModel() {

    companion object {
        const val TEXT = "text"
    }

    val state = MutableLiveData<StateViewState>(StateViewState.NoSave)

    val text = MutableLiveData<String>("").apply {
        observeForever { savedState.set(TEXT, value) }
    }

    val textWithState: LiveData<String>
        get() = savedState.getLiveData(TEXT)
}