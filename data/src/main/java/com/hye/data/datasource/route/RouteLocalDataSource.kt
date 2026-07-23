package com.hye.data.datasource.route

import com.hye.domain.model.arrival.RouteConfig
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode
import kotlinx.coroutines.flow.Flow

interface RouteLocalDataSource {
    suspend fun saveRouteInfo(
        stop: BusStop,
        mode: SelectionMode
    )

    suspend fun getSavedRouteInfos(): Pair<BusStop?, BusStop?>

    val savedRouteFlow: Flow<RouteConfig?>
}