package com.example.helloworld.data.appdetails

import com.example.helloworld.domain.appcard.AppCategory
import com.example.helloworld.domain.appdetails.AppDetails
import com.example.helloworld.domain.appdetails.GetAppDetailsUseCase
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AppDetailsRepositoryImplTest {
    val mockDao: AppDetailsDao = mockk()
    val mockApi: AppApi = mockk()
    val mockMapper: AppDetailsMapper = mockk()
    val mockEntityMapper: AppDetailsEntityMapper = mockk()

    val domainTest: AppDetails = AppDetails(
        "1",
        "name",
        "dev",
        AppCategory.APP,
        0,
        1f,
        "localhost",
        null,
        "description",
        false
    )

    val dtoTest: AppDetailsDto = AppDetailsDto(
        "1",
        "name",
        "dev",
        AppCategory.APP,
        0,
        1.toDouble(),
        "localhost",
        null,
        "description",
    )

    val entityTest: AppDetailsEntity = AppDetailsEntity(
        "1",
        "name",
        "dev",
        AppCategory.APP,
        0,
        1f,
        "localhost",
        null,
        "description",
    )

    private lateinit var appDetailsRepository: AppDetailsRepositoryImpl

    @Before
    fun setup() {
        // Clear mocks state before running tests
        clearAllMocks()

        // repo
        appDetailsRepository = AppDetailsRepositoryImpl(
            mockApi,
            mockDao,
            mockMapper,
            mockEntityMapper)
    }

    @After
    fun teardown() {
        // Clean up after each test
        unmockkAll()
    }

    @Test
    fun `call getAppDetails with empty database EXPECT appApi and dao-insertAppDetails were called once`() = runBlocking {
        // --- Setup Test Data ---
        val testId = "1"

        // --- Stubbing Behavior (Telling the Mocks what to return) ---

        // 1. Stub DAO: Make getAppDetails return a Flow that emits null initially
        every { mockDao.getAppDetails(testId) } returns flowOf(null)

        // 2. Stub API: When appApi.getAppDetails is called, it must return our test DTO
        coEvery { mockApi.getAppDetails(testId) } returns dtoTest

        // 3. Stub Mappers: Define how the mocks should transform data
        every { mockMapper.toDomain(dtoTest) } returns domainTest // API -> Domain
        every { mockEntityMapper.toEntity(domainTest) } returns entityTest // Domain -> Entity

        // 4. Stub DAO Write: We don't care about the return value of insertAppDetails, just that it's called.
        justRun { mockDao.insertAppDetails(entityTest) }

        // --- Execution (Calling the method under test) ---
        val resultFlow = appDetailsRepository.getAppDetails(testId)

        // Collect the flow to trigger all logic paths within the repository
        val finalResult = resultFlow.first()

        // --- Verification (Asserting what happened) ---

        // 1. Verify that the API was called exactly once because DAO returned null
        coVerify(exactly = 1) { mockApi.getAppDetails(testId) }

        // 2. Verify that the DAO write method was called exactly once with the correct entity
        verify(exactly = 1) { mockDao.insertAppDetails(entityTest) }
    }

    @Test
    fun `call getAppDetails EXPECT equal resulting and test domains`() = runBlocking {
        // --- Setup Test Data ---
        val testId = "1"

        // --- Stubbing Behavior (Telling the Mocks what to return) ---

        // 1. Stub DAO: Make getAppDetails return a Flow that emits null initially
        every { mockDao.getAppDetails(testId) } returns flowOf(null)

        // 2. Stub API: When appApi.getAppDetails is called, it must return our test DTO
        coEvery { mockApi.getAppDetails(testId) } returns dtoTest

        // 3. Stub Mappers: Define how the mocks should transform data
        every { mockMapper.toDomain(dtoTest) } returns domainTest // API -> Domain
        every { mockEntityMapper.toEntity(domainTest) } returns entityTest // Domain -> Entity

        // 4. Stub DAO Write: We don't care about the return value of insertAppDetails, just that it's called.
        justRun { mockDao.insertAppDetails(entityTest) }

        // --- Execution (Calling the method under test) ---
        val resultFlow = appDetailsRepository.getAppDetails(testId)

        // Collect the flow to trigger all logic paths within the repository
        val finalResult = resultFlow.first()

        // --- Verification (Asserting what happened) ---

        // Assert the final returned value is correct
        assertEquals(domainTest, finalResult)
    }

    @Test
    fun `call getAppDetails with cached entity EXPECT api and insertAppDetails weren't called`() = runBlocking {
        // --- Setup Test Data ---
        val testId = "1"

        // --- Stubbing Behavior (Telling the Mocks what to return) ---

        // 1. Stub DAO: Make getAppDetails return a Flow that emits null initially
        every { mockDao.getAppDetails(testId) } returns flowOf(entityTest)

        // 2. Stub API: When appApi.getAppDetails is called, it must return our test DTO
        coEvery { mockApi.getAppDetails(testId) } returns dtoTest

        // 3. Stub Mappers: Define how the mocks should transform data
        every { mockMapper.toDomain(dtoTest) } returns domainTest // API -> Domain
        every { mockEntityMapper.toEntity(domainTest) } returns entityTest // Domain -> Entity
        every { mockEntityMapper.toDomain(entityTest) } returns domainTest // Domain -> Entity

        // --- Execution (Calling the method under test) ---
        val resultFlow = appDetailsRepository.getAppDetails(testId)

        // Collect the flow to trigger all logic paths within the repository
        val finalResult = resultFlow.first()

        // --- Verification (Asserting what happened) ---

        // 1. Verify that the API was called exactly once because DAO returned null
        coVerify(exactly = 0) { mockApi.getAppDetails(testId) }

        // 2. Verify that the DAO write method was called exactly once with the correct entity
        verify(exactly = 0) { mockDao.insertAppDetails(entityTest) }
    }

    @Test
    fun `call toggleWishList when an app wasn't in a wishlist before EXPECT dao-updateWishlistStatus with True`() = runBlocking {
        val testId: String = "1"
        var currentEntity : AppDetailsEntity = entityTest
        val entityWishlisted : AppDetailsEntity = entityTest.copy(isInWishlist = true)

        // Make getAppDetails return a Flow that emits null initially
        every { mockDao.getAppDetails(testId) } returns flowOf(currentEntity)

        // Run updateWishlistStatus
        coJustRun { mockDao.updateWishlistStatus(testId, !currentEntity.isInWishlist) }
        // If updateWishListStatus has run, entity's wishlist status is updated
        currentEntity = entityWishlisted
        // Run toggleWishlist
        appDetailsRepository.toggleWishlist(testId)

        assertEquals(entityWishlisted, currentEntity)

        coVerify(exactly = 1){ mockDao.updateWishlistStatus(testId, currentEntity.isInWishlist) }

    }

    @Test
    fun `call toggleWishList when an app was in a wishlist before EXPECT dao-updateWishlistStatus with False`() = runBlocking {
        val testId: String = "1"
        val entityWishlisted : AppDetailsEntity = entityTest.copy(isInWishlist = true)
        var currentEntity : AppDetailsEntity = entityWishlisted

        // Make getAppDetails return a Flow that emits null initially
        every { mockDao.getAppDetails(testId) } returns flowOf(currentEntity)

        // Run updateWishlistStatus
        coJustRun { mockDao.updateWishlistStatus(testId, false) }
        // If updateWishListStatus has run, entity's wishlist status is updated
        currentEntity = entityTest
        // Run toggleWishlist
        appDetailsRepository.toggleWishlist(testId)

        assertEquals(entityTest, currentEntity)

        coVerify(exactly = 1){ mockDao.updateWishlistStatus(testId, currentEntity.isInWishlist) }
    }
}