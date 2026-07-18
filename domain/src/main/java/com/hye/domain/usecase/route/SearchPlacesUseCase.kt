package com.hye.domain.usecase.route

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.Place
import com.hye.domain.repository.PlaceRepository
import javax.inject.Inject

class SearchPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {
    suspend operator fun invoke(keyword: String): ResultWrapper<List<Place>> {
        if (keyword.trim().isEmpty()) {
            return ResultWrapper.Success(emptyList())
        }

        return placeRepository.searchPlaces(keyword)
    }
}