package com.example.helloworld.data.appcard

import com.example.helloworld.data.appcard.dto.AppCardDto
import com.example.helloworld.data.appcard.dto.AppCardEntity
import com.example.helloworld.domain.appcard.AppCard
import com.example.helloworld.data.appdetails.CategoryConverter
import javax.inject.Inject

class AppCardMapper @Inject constructor() {
    private val converter = CategoryConverter()
    fun toDomain(dto: AppCardDto): AppCard = AppCard(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        category = converter.toCategory(dto.category),
        iconUrl = dto.iconUrl
    )

    fun toAppCard(appEntity: AppCardEntity): AppCard = AppCard(
        id = appEntity.id,
        name = appEntity.name,
        description = appEntity.description,
        category = converter.toCategory(appEntity.category),
        iconUrl = appEntity.iconUrl
    )

    fun toAppCardEntity(domain: AppCard): AppCardEntity = AppCardEntity(
        id = domain.id,
        name = domain.name,
        description = domain.description,
        category = converter.fromCategory(domain.category),
        iconUrl = domain.iconUrl
    )
}