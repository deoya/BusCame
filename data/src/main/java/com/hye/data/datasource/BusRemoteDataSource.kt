package com.hye.data.datasource

import com.hye.data.remote.dto.station.bus.common.BusSttnItemDto

interface BusRemoteDataSource {

    fun canHandle(lat: Double, lng: Double): Boolean

    suspend fun getNearbyStations(
        lat: Double,
        lng: Double
    ): List<BusSttnItemDto>
}