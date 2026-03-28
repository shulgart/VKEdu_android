package com.example.helloworld.presentation.appcard

sealed interface AppCardEvent {
    data object LogoClicked : AppCardEvent
}