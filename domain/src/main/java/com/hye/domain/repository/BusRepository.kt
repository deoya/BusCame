package com.hye.domain.repository

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode

interface BusRepository {
    suspend fun getNearbyBusStops(lat: Double, lng: Double): ResultWrapper<List<BusStop>>

    suspend fun saveBusStopInfo(stop: BusStop, mode: SelectionMode): ResultWrapper<Unit>

    suspend fun getSavedRouteInfos(): ResultWrapper<Pair<BusStop?, BusStop?>>

}