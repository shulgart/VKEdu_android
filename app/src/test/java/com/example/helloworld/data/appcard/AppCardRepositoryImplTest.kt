package com.example.helloworld.data.appcard

import com.example.helloworld.data.appcard.dto.AppCardDto
import com.example.helloworld.data.appcard.dto.AppCardEntity
import com.example.helloworld.domain.appcard.AppCard
import com.example.helloworld.domain.appcard.AppCategory
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class AppCardRepositoryImplTest {

    val domainTest: AppCard = AppCard(
        "1",
        "name",
        "dev",
        AppCategory.APP,
        "localhost"
    )

    val dtoTest: AppCardDto = AppCardDto(
        "1",
        "name",
        "dev",
        "Приложения",
        "localhost"
    )

    val entityTest: AppCardEntity = AppCardEntity(
        "1",
        "name",
        "dev",
        "Приложения",
        "localhost",
    )

    val domainList: List<AppCard> = listOf(domainTest, domainTest, domainTest)
    val dtoList: List<AppCardDto> = listOf(dtoTest, dtoTest, dtoTest)
    val entityList: List<AppCardEntity> = listOf(entityTest, entityTest, entityTest)

    val mockMapper: AppCardMapper = mockk()
    val mockApi: AppCardApi = mockk()
    val mockDao: AppCardDao = mockk()
    private lateinit var appCardRepository: AppCardRepositoryImpl

    @Before
    fun setup() {
        // Clear mocks state before running tests
        clearAllMocks()

        // repo
        appCardRepository = AppCardRepositoryImpl(
            mockApi,
            mockDao,
            mockMapper
        )
    }

    @After
    fun teardown() {
        // Clean up after each test
        unmockkAll()
    }

    // Verify mappers work inside the repository
    @Test
    fun `check mapper calls, local db is full EXPECT only toAppCard call at least once`() = runBlocking {

        // Stub mock mappers with outputs
        every { mockMapper.toAppCard(entityTest) } returns domainTest
        every { mockMapper.toAppCardEntity(domainTest) } returns entityTest
        every { mockMapper.toDomain(dtoTest) } returns domainTest

        // Stub the api
        coEvery { mockApi.get() } returns dtoList

        // Stub the dao, it has something in store
        coEvery { mockDao.getApps() } returns flowOf(
            entityList
        )

        val resultFlow = appCardRepository.get()
        val resultList = resultFlow.first()

        // Verify mapper
        verify(atLeast = 1) { mockMapper.toAppCard(entityTest) }
        verify(exactly = 0) { mockMapper.toAppCardEntity(domainTest) }
        verify(exactly = 0) { mockMapper.toDomain(dtoTest) }
    }

    @Test
    fun `check mapper calls, local db is empty EXPECT toAppCardEntity, toDomain calls`() = runBlocking {

        // Stub mock mappers with outputs
        every { mockMapper.toAppCard(entityTest) } returns domainTest
        every { mockMapper.toAppCardEntity(domainTest) } returns entityTest
        every { mockMapper.toDomain(dtoTest) } returns domainTest

        // Stub the api
        coEvery { mockApi.get() } returns dtoList

        // Stub the dao, it is empty
        coEvery { mockDao.getApps() } returns flowOf(emptyList<AppCardEntity>())

        // Just run the insert apps method
        justRun { mockDao.insertApps(entityList) }

        val resultFlow = appCardRepository.get()
        val resultList = resultFlow.first()

        // Verify mapper
        verify(exactly = 0) { mockMapper.toAppCard(entityTest) }
        verify(atLeast = 1) { mockMapper.toAppCardEntity(domainTest) }
        verify(atLeast = 1) { mockMapper.toDomain(dtoTest) }
    }

    // Verify Api
    @Test
    fun `check api calling, when local db is empty EXPECT mockApi calls exactly 1`() = runBlocking {

        // Stub mock mappers with outputs
        every { mockMapper.toAppCard(entityTest) } returns domainTest
        every { mockMapper.toAppCardEntity(domainTest) } returns entityTest
        every { mockMapper.toDomain(dtoTest) } returns domainTest

        // Stub the api
        coEvery { mockApi.get() } returns dtoList

        // Stub the dao, it is empty
        coEvery { mockDao.getApps() } returns flowOf(emptyList<AppCardEntity>())

        // Just run the insert apps method
        justRun { mockDao.insertApps(entityList) }

        val resultFlow = appCardRepository.get()
        val resultList = resultFlow.first()

        // Verify api is calling exactly once
        coVerify(exactly = 1) { mockApi.get() }
    }

    // Verify Dao
    @Test
    fun `check dao insertApps, when local db is empty EXPECT insertApps calls exactly 1`() = runBlocking {

        // Stub mock mappers with outputs
        every { mockMapper.toAppCard(entityTest) } returns domainTest
        every { mockMapper.toAppCardEntity(domainTest) } returns entityTest
        every { mockMapper.toDomain(dtoTest) } returns domainTest

        // Stub the api
        coEvery { mockApi.get() } returns dtoList

        // Stub the dao, it is empty
        coEvery { mockDao.getApps() } returns flowOf(emptyList<AppCardEntity>())

        // Just run the insert apps method
        justRun { mockDao.insertApps(entityList) }

        val resultFlow = appCardRepository.get()
        val resultList = resultFlow.first()

        // Verify dao calls exactly once
        coVerify(exactly = 1) { mockDao.getApps() }
        coVerify(exactly = 1) { mockDao.insertApps(entityList) }
    }

    @Test
    fun `check dao getApps, when local db is full EXPECT insertApps calls exactly 0, getApps exactly 1`() = runBlocking {

        // Stub mock mappers with outputs
        every { mockMapper.toAppCard(entityTest) } returns domainTest
        every { mockMapper.toAppCardEntity(domainTest) } returns entityTest
        every { mockMapper.toDomain(dtoTest) } returns domainTest

        // Stub the api
        coEvery { mockApi.get() } returns dtoList

        // Stub the dao, it is empty
        coEvery { mockDao.getApps() } returns flowOf(entityList)

        // Just run the insert apps method
        justRun { mockDao.insertApps(entityList) }

        val resultFlow = appCardRepository.get()
        val resultList = resultFlow.first()

        // Verify dao calls exactly once
        coVerify(exactly = 1) { mockDao.getApps() }
        coVerify(exactly = 0) { mockDao.insertApps(entityList) }
    }

    // Verify output list
    @Test
    fun `check output list, local db is full EXPECT output list equals the test one`() = runBlocking {

        // Stub mock mappers with outputs
        every { mockMapper.toAppCard(entityTest) } returns domainTest
        every { mockMapper.toAppCardEntity(domainTest) } returns entityTest
        every { mockMapper.toDomain(dtoTest) } returns domainTest

        // Stub the api
        coEvery { mockApi.get() } returns dtoList

        // Stub the dao, it has something in store
        coEvery { mockDao.getApps() } returns flowOf(
            entityList
        )

        val resultFlow = appCardRepository.get()
        val resultList = resultFlow.first()

        // Verify outputs
        assertEquals(domainList, resultList)
    }

    @Test
    fun `check dao getApps, when local db is empty EXPECT output equals test`() = runBlocking {

        // Stub mock mappers with outputs
        every { mockMapper.toAppCard(entityTest) } returns domainTest
        every { mockMapper.toAppCardEntity(domainTest) } returns entityTest
        every { mockMapper.toDomain(dtoTest) } returns domainTest

        // Stub the api
        coEvery { mockApi.get() } returns dtoList

        // Stub the dao, it is empty
        coEvery { mockDao.getApps() } returns flowOf(entityList)

        // Just run the insert apps method
        justRun { mockDao.insertApps(entityList) }

        val resultFlow = appCardRepository.get()
        val resultList = resultFlow.first()

        // Verify outputs
        assertEquals(domainList, resultList)
    }

}