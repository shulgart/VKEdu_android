package com.example.helloworld.presentation.appdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.domain.appdetails.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id: String = checkNotNull(savedStateHandle["id"])
    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        getAppDetails()
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else {
                currentState
            }
        }
    }

    fun getAppDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = AppDetailsState.Loading
            runCatching {
                val appDetails = async{getAppDetailsUseCase(id)}
                _state.value = AppDetailsState.Content(
                    appDetails.await(),
                    descriptionCollapsed = false
                )
            }.onFailure {
                _state.value = AppDetailsState.Error
            }

        }
    }
}