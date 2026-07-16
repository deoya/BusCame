package com.hye.data.datasource.seoul

import com.hye.data.datasource.BusRemoteDataSource
import com.hye.data.di.qualifier.SeoulBusApiKey
import com.hye.data.remote.api.SeoulBusApi
import com.hye.data.remote.dto.station.bus.common.BusSttnItemDto
import javax.inject.Inject

class SeoulBusRemoteDataSourceImpl @Inject constructor(
    private val api: SeoulBusApi,
    @SeoulBusApiKey private val apiKey: String
) : BusRemoteDataSource {

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
            serviceKey = apiKey,
            longitude = lng,
            latitude = lat
        ).msgBody?.itemList ?: emptyList()
    }
}