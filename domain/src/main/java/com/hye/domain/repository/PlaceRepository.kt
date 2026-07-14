package com.hye.domain.repository

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.Place

interface PlaceRepository {
    suspend fun searchPlaces(keyword: String): ResultWrapper<List<Place>>
}