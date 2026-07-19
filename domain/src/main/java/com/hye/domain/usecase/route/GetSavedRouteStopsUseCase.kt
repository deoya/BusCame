package com.hye.domain.usecase.route

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.BusStop
import com.hye.domain.repository.BusRepository
import javax.inject.Inject

class GetSavedRouteStopsUseCase @Inject constructor(
    private val busRepository: BusRepository
) {
    suspend operator fun invoke(): ResultWrapper<Pair<BusStop?, BusStop?>> {
        return busRepository.getSavedRouteStops()
    }
}