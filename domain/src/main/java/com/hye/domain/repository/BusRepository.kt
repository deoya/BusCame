package com.hye.domain.repository

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.BusStop

interface BusRepository {
    suspend fun getNearbyBusStops(lat: Double, lng: Double): ResultWrapper<List<BusStop>>
}