package com.example.helloworld.domain.appdetails

// Assuming necessary imports for JUnit 4 and Mockito/MockK are available
import com.example.helloworld.domain.appcard.AppCategory
import org.junit.Before
import org.junit.After
import org.junit.Test // Using JUnit 4 @Test
import io.mockk.* // Or your preferred mocking library
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals

class GetAppDetailsUseCaseTest {

    private val mockRepository: AppDetailsRepository = mockk()
    private val appDetails: AppDetails = AppDetails(
        id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
        name = "Гильдия Героев: Экшен ММО РПГ",
        developer = "VK Play",
        category = AppCategory.GAME,
        ageRating = 12,
        size = 223.7f,
        screenshotUrlList = listOf(
            "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
            "https://static.rustore.ru/imgproxy/dZCvNtRKKFpzOmGlTxLszUPmwi661IhXynYZGsJQvLw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/60ec4cbc-dcf6-4e69-aa6f-cc2da7de1af6.jpg@webp",
            "https://static.rustore.ru/imgproxy/g5whSI1uNqaL2TUO7TFfM8M63vXpWXNCm2vlX4Ahvc4/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/c2dde8bc-c4ab-482a-80a5-2789149f598d.jpg@webp",
            "https://static.rustore.ru/imgproxy/TjeurtC7BczOVJt74XhjGYuQnG1l4rx6zpDqyMb00GY/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/08318f76-7a9c-43aa-b4a7-1aa878d00861.jpg@webp",
        ),
        iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
        description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!"
    )
    private lateinit var useCase: GetAppDetailsUseCase

    @Before
    fun setup() {
        // Initialize the UseCase before each test
        useCase = GetAppDetailsUseCase(mockRepository)
        // Clear mocks state before running tests
        clearMocks(mockRepository)
    }

    @After
    fun teardown() {
        // Clean up after each test
        unmockkAll()
    }

    @Test
    fun `call appDetailsRepository getAppDetails EXPECT called once`() = runBlocking { // Placeholder for structured execution if needed, otherwise use standard blocking calls

        // Arrange
        val testId = "fa2e31b8-1234-4cf7-9914-108a170a1b01"

        // Mock the repository call to return a Flow
        every { mockRepository.getAppDetails(testId) } returns flowOf(appDetails)

        // Act & Assert (Combining execution and verification for simplicity without runTest structure)
        val resultFlow = useCase(testId)

        // Collect the flow synchronously to trigger the repository call and check results
        val collectedValue = resultFlow.first()

        // Verify interaction count using MockK's verification block structure (if available/preferred over JUnit 4 verify)
        verify(exactly = 1) { mockRepository.getAppDetails(testId) }
    }

    @Test
    fun `get appDetails by incorrect testId EXPECT empty flow`() = runBlocking { // Placeholder for structured execution if needed, otherwise use standard blocking calls
        // Arrange
        val testIdNotFound = "non-existent-id-999"

        // Mock the repository to return an empty flow, simulating 'not found'
        every { mockRepository.getAppDetails(testIdNotFound) } returns flowOf<AppDetails>()

        // Act
        val resultFlow = useCase(testIdNotFound)

        // Assert (We assert that collecting the flow results in no value being emitted)
        var collectedValue: AppDetails? = null
        resultFlow.collect {
            collectedValue = it // This block should ideally not execute if the flow is truly empty
        }

        assert(collectedValue == null) { "Expected the use case to return a null/empty result for non-existent ID." }
    }

    @Test
    fun `invoke with empty id EXPECT empty string is passed to repo`() = runBlocking {
        // Arrange
        val testIdEmpty = ""

        // Mock the repository call for the empty string input
        every { mockRepository.getAppDetails(testIdEmpty) } returns flowOf(appDetails)

        // Act
        val resultFlow = useCase(testIdEmpty)

        // Assert (Verify that the repository was called specifically with an empty string)
        verify(exactly = 1) { mockRepository.getAppDetails(testIdEmpty) }
    }

    @Test
    fun `invoke with an exact id EXPECT appDetails equal to test appDetails` () = runBlocking {
        val testIdDifferent = "fa2e31b8-1234-4cf7-9914-108a170a1b01"

        // FIX: Mock the repository to return a flow containing the actual appDetails object.
        every { mockRepository.getAppDetails(testIdDifferent) } returns flowOf(appDetails)

        // Act
        val resultFlow = useCase(testIdDifferent)

        // Assert (Using .first() is cleaner and more idiomatic for testing single-emission flows)
        val collectedValue = resultFlow.first()

        assertEquals(appDetails, collectedValue)
    }

    // What if our repository is spewing out the same test appDetails and nothing else?
    @Test
    fun `invoke with a different id EXPECT appDetails NOT equal to test appDetails` () = runBlocking {
        val testIdDifferent = "DIFFERENTfa2e31b8-1234-4cf7-9914-108a170a1b01"

        // Mock the repository
        every { mockRepository.getAppDetails(testIdDifferent) } returns flowOf<AppDetails>()

        // Act
        val resultFlow = useCase(testIdDifferent)

        // Assert
        var collectedValue: AppDetails? = null
        resultFlow.collect {
            collectedValue = it
        }

        assertNotEquals(appDetails, collectedValue)
    }

}
