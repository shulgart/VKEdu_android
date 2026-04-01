package com.example.helloworld.presentation.appcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.domain.appcard.AppCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppCardViewModel @Inject constructor(
    private val appRepo : AppCardRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AppCardState>(AppCardState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppCardEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            getAppCard()
        }
    }

    fun clickLogo() {
        viewModelScope.launch {
            _events.send(AppCardEvent.LogoClicked)
        }
    }

    fun showLogoClickedMessage() {
        _state.update { currentState ->
            if (currentState is AppCardState.Content) {
                currentState.copy(logoClicked = true)
            } else {
                currentState
            }
        }
    }

    fun onCardClicked(navigate: (String) -> Unit) {
        navigate("screen_b") // Trigger navigation via the passed callback
    }

    suspend fun getAppCard() {
        _state.value = AppCardState.Loading

        runCatching {

            appRepo.get().collect { appCards ->
                _state.value = AppCardState.Content(
                    appCards = appCards,
                    logoClicked = false
                )
            }

        }.onFailure {
            _state.value = AppCardState.Error
        }
    }
}