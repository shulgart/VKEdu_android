package com.example.helloworld.presentation.appcard

import androidx.compose.runtime.Immutable
import com.example.helloworld.domain.appcard.AppCard

@Immutable
sealed interface AppCardState {
    data object Error : AppCardState
    data object Loading : AppCardState
    data class Content(
        val appCards: List<AppCard>,
        val logoClicked: Boolean,
    ) : AppCardState
}