package com.example.helloworld.data.appdetails

import com.example.helloworld.domain.appdetails.AppDetails
import com.example.helloworld.domain.appcard.AppCategory
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test

class AppDetailsMapperTest {
    private lateinit var mapper: AppDetailsMapper

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

    @Before
    fun setUp() {
        mapper = AppDetailsMapper()
    }

    @Test
    fun `run mapper on dtoTest EXPECT domainTest`() {
        val result = mapper.toDomain(dtoTest)

        assertEquals(domainTest, result)
    }

    @Test
    fun `run mapper on dto with all optional fields null EXPECT domain with all optional fields null`() {
        // 1. Arrange: Create an Entity object where optional fields are explicitly null
        val dtoWithNullOptionals: AppDetailsDto = AppDetailsDto(
            "2",
            "null_name",
            "dev",
            AppCategory.APP,
            0,
            1.toDouble(),
            "localhost",
            null, // <-- Explicitly null for the nullable field
            ""
        )

        // 2. Act: Map to Domain
        val resultDomain = mapper.toDomain(dtoWithNullOptionals)

        // 3. Assert: Verify the resulting domain also has nulls in those positions
        assertNull(resultDomain.screenshotUrlList)
    }
}