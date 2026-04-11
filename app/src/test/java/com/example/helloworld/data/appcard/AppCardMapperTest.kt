package com.example.helloworld.data.appcard

import com.example.helloworld.data.appcard.dto.AppCardDto
import com.example.helloworld.data.appcard.dto.AppCardEntity
import com.example.helloworld.domain.appcard.AppCard
import com.example.helloworld.domain.appcard.AppCategory
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class AppCardMapperTest {
    private lateinit var mapper: AppCardMapper

    val domainTest: AppCard = AppCard(
        "1",
        "name",
        "dev",
        AppCategory.APP,
        "localhost"
    )

    val entityTest: AppCardEntity = AppCardEntity(
        "1",
        "name",
        "dev",
        "Приложения",
        "localhost"
    )

    val dtoTest: AppCardDto = AppCardDto(
        "1",
        "name",
        "dev",
        "Приложения",
        "localhost"
    )

    @Before
    fun setUp() {
        mapper = AppCardMapper()
    }

    @Test
    fun `run mapper on domainTest EXPECT entityTest`() {
        val result = mapper.toAppCardEntity(domainTest)

        assertEquals(entityTest, result)
    }

    @Test
    fun `run mapper on entityTest EXPECT domainTest`() {
        val result = mapper.toAppCard(entityTest)

        assertEquals(domainTest, result)
    }

    @Test
    fun `run mapper on dto EXPECT domainTest`() {
        val result = mapper.toDomain(dtoTest)

        assertEquals(domainTest, result)
    }

    @Test
    fun `run entity-domain mapper in a circle EXPECT entityTest`() {
        val resultDomain = mapper.toAppCard(entityTest)
        val result = mapper.toAppCardEntity(resultDomain)

        assertEquals(entityTest, result)
    }

    @Test
    fun `run domain-entity mapper in a circle EXPECT domainTest`() {
        val resultEntity = mapper.toAppCardEntity(domainTest)
        val result = mapper.toAppCard(resultEntity)

        assertEquals(domainTest, result)
    }
}