package com.example.helloworld.presentation.appdetails

import androidx.compose.runtime.Immutable
import com.example.helloworld.domain.appdetails.AppDetails

@Immutable
sealed interface AppDetailsState {
    data object Error : AppDetailsState
    data object Loading : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val descriptionCollapsed: Boolean,
        val isInWishlist: Boolean
    ) : AppDetailsState
}