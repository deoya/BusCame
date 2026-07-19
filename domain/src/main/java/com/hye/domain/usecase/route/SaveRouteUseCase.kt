package com.hye.domain.usecase.route

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode
import com.hye.domain.repository.BusRepository
import javax.inject.Inject

class SaveRouteUseCase @Inject constructor(
    private val routeRepository: BusRepository
) {
    suspend operator fun invoke(stop: BusStop, mode: SelectionMode): ResultWrapper<Unit> {
        return routeRepository.saveRouteStop(stop, mode)
    }
}