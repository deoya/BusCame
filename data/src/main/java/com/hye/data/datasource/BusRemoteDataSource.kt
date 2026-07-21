package com.hye.data.datasource

import com.hye.data.remote.dto.bus.common.BusSttnItemDto

interface BusRemoteDataSource {
    val priority: Int

    fun canHandle(lat: Double, lng: Double): Boolean

    suspend fun getNearbyStations(
        lat: Double,
        lng: Double
    ): List<BusSttnItemDto>
}