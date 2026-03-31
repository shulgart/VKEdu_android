package com.example.helloworld.data.appdetails

import com.example.helloworld.domain.appdetails.AppDetails

class AppDetailsEntityMapper {

    fun toEntity(domain: AppDetails): AppDetailsEntity = AppDetailsEntity(
        id = domain.id,
        name = domain.name,
        developer = domain.developer,
        category = domain.category,
        ageRating = domain.ageRating,
        size = domain.size,
        iconUrl = domain.iconUrl,
        screenshots = null,
        description = domain.description,
        isInWishlist = domain.isInWishlist
    )

    fun toDomain(entity: AppDetailsEntity): AppDetails = AppDetails(
        id = entity.id,
        name = entity.name,
        developer = entity.developer,
        category = entity.category,
        ageRating = entity.ageRating,
        size = entity.size,
        iconUrl = entity.iconUrl,
        screenshotUrlList = null,
        description = entity.description,
        isInWishlist = entity.isInWishlist
    )
}