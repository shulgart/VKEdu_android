package com.example.helloworld.presentation.appcard

import com.example.helloworld.presentation.appdetails.AppDetails
import com.example.helloworld.presentation.appdetails.AppDetailsEvent
import com.example.helloworld.presentation.appdetails.AppDetailsState
import com.example.helloworld.presentation.appdetails.Category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.R
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
                // Эмулируем загрузку с бэкенда
                delay(1.seconds)

                // В будущем заменим этот метод на вызов API.
                val browserApp = AppShort(
                    "Яндекс.Браузер - с Алисой",
                    "Быстрый и безопасный браузер",
                    AppCategory.TOOLS,
                    R.drawable.yandex_brow
                )

                val sberApp = AppShort(
                    "СберБанк Онлайн - с Салютом",
                    "Больше чем банк",
                    AppCategory.FINANCES,
                    R.drawable.sber
                )

                val mailApp = AppShort(
                    "Почта Mail.ru",
                    "Почтовый клиент для любых ящиков",
                    AppCategory.TOOLS,
                    R.drawable.mail_logo
                )

                val navApp = AppShort(
                    "Яндекс Навигатор",
                    "Парковки и заправки - по пути",
                    AppCategory.TRANSPORT,
                    R.drawable.navigator
                )

                val mtsApp = AppShort(
                    "Мой МТС",
                    "Мой МТС - центр экосистемы МТС",
                    AppCategory.TOOLS,
                    R.drawable.mts
                )

                val yandexApp = AppShort(
                    "Яндекс - с Алисой",
                    "Яндекс - поиск всегда под рукой",
                    AppCategory.TOOLS,
                    R.drawable.yandex
                )

                val appCards = listOf(browserApp, sberApp, mailApp, navApp, mtsApp, yandexApp)

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