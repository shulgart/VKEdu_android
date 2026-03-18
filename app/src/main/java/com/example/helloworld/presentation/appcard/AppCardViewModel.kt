package com.example.helloworld.presentation.appcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.R
import com.example.helloworld.data.appcard.AppCardRepositoryImpl
import com.example.helloworld.domain.appcard.AppCategory
import com.example.helloworld.domain.appcard.AppCard
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AppCardViewModel : ViewModel() {
    private val appRepo = AppCardRepositoryImpl()
    private val _state = MutableStateFlow<AppCardState>(AppCardState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppCardEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        getAppCard()
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
        // Do some logic
        navigate("screen_b") // Trigger navigation via the passed callback
    }

    fun getAppCard() {
        viewModelScope.launch {
            _state.value = AppCardState.Loading

            runCatching {

                val appCards = appRepo.get()

                _state.value = AppCardState.Content(
                    appCards = appCards,
                    logoClicked = false,
                )
            }.onFailure {
                _state.value = AppCardState.Error
            }
        }
    }
}