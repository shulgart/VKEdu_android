package com.example.helloworld.presentation.appcard

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AppCardState {
    data object Error : AppCardState
    data object Loading : AppCardState
    data class Content(
        val appCards: List<AppShort>,
        val logoClicked: Boolean,
    ) : AppCardState
}