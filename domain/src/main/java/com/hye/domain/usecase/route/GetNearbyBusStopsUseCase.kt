package com.hye.domain.usecase.route

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.BusStop
import com.hye.domain.repository.BusRepository
import javax.inject.Inject

class GetNearbyBusStopsUseCase @Inject constructor(
    private val repository: BusRepository
) {
    suspend operator fun invoke(lat: Double, lng: Double): ResultWrapper<List<BusStop>> {
        return repository.getNearbyBusStops(lat, lng)
    }
}