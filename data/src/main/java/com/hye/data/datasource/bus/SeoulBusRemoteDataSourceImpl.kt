package com.hye.data.datasource.bus

import com.hye.data.remote.api.SeoulBusApi
import com.hye.data.remote.dto.bus.common.BusSttnItemDto
import javax.inject.Inject

class SeoulBusRemoteDataSourceImpl @Inject constructor(
    private val api: SeoulBusApi,
) : BusRemoteDataSource {

    override val priority: Int = 100

    override fun canHandle(
        lat: Double,
        lng: Double
    ): Boolean {
        return lat in 37.42..37.70 &&
                lng in 126.76..127.18
    }

    override suspend fun getNearbyStations(
        lat: Double,
        lng: Double
    ): List<BusSttnItemDto> {

        return api.getNearbyStations(
            longitude = lng,
            latitude = lat
        ).msgBody?.itemList ?: emptyList()
    }
}