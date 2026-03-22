package com.example.helloworld.presentation.appcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloworld.R
import com.example.helloworld.presentation.appdetails.AppDetailsContent
import com.example.helloworld.presentation.appdetails.AppDetailsError
import com.example.helloworld.presentation.appdetails.AppDetailsEvent
import com.example.helloworld.presentation.appdetails.AppDetailsLoading
import com.example.helloworld.presentation.appdetails.AppDetailsState
import com.example.helloworld.presentation.appdetails.AppDetailsViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun AppCardScreen(
    viewModel: AppCardViewModel,
    onGoForward: () -> Unit
) {
//    val viewModel = viewModel<AppCardViewModel>()
    val state by viewModel.state.collectAsState()
    val events = viewModel.events

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveEvents(
        events = events,
        snackbarHostState = snackbarHostState,
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { contentPadding ->
        when (val currentState = state) {
            is AppCardState.Loading -> {
                AppDetailsLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .padding(contentPadding),
                )
            }

            is AppCardState.Error -> {
                AppCardError(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .padding(contentPadding),
                )
            }

            is AppCardState.Content -> {
                AppCardContent(
                    list = currentState.appCards,
                    onGoForward = onGoForward,
                    onClickLogo = { viewModel.clickLogo() },
                    modifier=Modifier
                        .background(Color.LightGray)
                        .fillMaxHeight()
                        .safeDrawingPadding()
                        .padding(contentPadding)
                )
            }
        }
    }
}

@Composable
private fun ObserveEvents(
    events: Flow<AppCardEvent>,
    snackbarHostState: SnackbarHostState,
) {
    val logoClickedText = stringResource(R.string.logo_clicked)

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is AppCardEvent.LogoClicked -> {
                    snackbarHostState.showSnackbar(logoClickedText)
                }
            }
        }
    }
}