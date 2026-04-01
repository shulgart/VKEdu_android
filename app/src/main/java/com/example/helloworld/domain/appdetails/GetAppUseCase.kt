package com.example.helloworld.domain.appdetails

class GetAppDetailsUseCase(
    private val appDetailsRepository: AppDetailsRepository,
) {
    operator suspend fun invoke(id: String): AppDetails =
        appDetailsRepository.getAppDetails(id)
}