package com.example.helloworld.data.appcard

import com.example.helloworld.domain.appcard.AppCard
import com.example.helloworld.domain.appcard.AppCategory
import javax.inject.Inject

class AppCardMapper @Inject constructor() {
    fun toDomain(dto: AppCardDto): AppCard = AppCard(
        name = dto.name,
        description = dto.description,
        category = when(dto.category) {
            "Финансы" -> AppCategory.FINANCES
            "Инструменты" -> AppCategory.TOOLS
            "Транспорт" -> AppCategory.TRANSPORT
            else -> throw IllegalStateException("No such category")
        },
        icon = dto.icon
    )
}