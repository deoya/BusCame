package com.hye.domain.usecase.route

import javax.inject.Inject

data class BusStopsUseCase @Inject constructor(
    val getNearbyBusStopsUseCase: GetNearbyBusStopsUseCase,
)