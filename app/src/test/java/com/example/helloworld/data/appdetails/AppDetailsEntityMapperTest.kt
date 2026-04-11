package com.example.helloworld.data.appdetails

import com.example.helloworld.domain.appdetails.AppDetails
import com.example.helloworld.domain.appcard.AppCategory
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AppDetailsEntityMapperTest {
    private lateinit var mapper: AppDetailsEntityMapper

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

    @Before
    fun setUp() {
        mapper = AppDetailsEntityMapper()
    }

    @Test
    fun `run mapper on domainTest EXPECT entityTest`() {
        val result = mapper.toEntity(domainTest)

        assertEquals(entityTest, result)
    }

    @Test
    fun `run mapper on entityTest EXPECT domainTest`() {
        val result = mapper.toDomain(entityTest)

        assertEquals(domainTest, result)
    }

    // Round-trip domain -> toEntity -> toDomain -> domain
    @Test
    fun `run mapper on domainTest and then on resulted entity EXPECT domainTest`() {
        val resultEntity = mapper.toEntity(domainTest)
        val resultDomain = mapper.toDomain(resultEntity)


        assertEquals(domainTest, resultDomain)
    }

    // Round-trip entity -> toDomain -> toEntity -> entity
    @Test
    fun `run mapper on entityTest then on resulted domain EXPECT entityTest`() {
        val resultDomain = mapper.toDomain(entityTest)
        val resultEntity = mapper.toEntity(resultDomain)

        assertEquals(entityTest, resultEntity)
    }

    @Test
    fun `run mapper on entity with all optional fields null EXPECT domain with all optional fields null`() {
        // 1. Arrange: Create an Entity object where optional fields are explicitly null
        val entityWithNullOptionals: AppDetailsEntity = AppDetailsEntity(
            "2",
            "null_name",
            "dev",
            AppCategory.APP,
            0,
            1f,
            "localhost",
            null, // <-- Explicitly null for the nullable field
            ""
        )

        // 2. Act: Map to Domain
        val resultDomain = mapper.toDomain(entityWithNullOptionals)

        // 3. Assert: Verify the resulting domain also has nulls in those positions
        assertNull(resultDomain.screenshotUrlList)
    }
}