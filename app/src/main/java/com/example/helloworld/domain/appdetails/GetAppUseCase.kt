package com.example.helloworld.domain.appdetails

import com.example.helloworld.domain.appcard.AppCategory

import kotlinx.coroutines.flow.Flow

class GetAppDetailsUseCase(
    private val appDetailsRepository: AppDetailsRepository,
) {
    suspend operator fun invoke(id: String): Flow<AppDetails> =
        appDetailsRepository.getAppDetails(id)
}