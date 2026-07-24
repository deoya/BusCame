package com.hye.data.datasource.bus

import com.hye.data.remote.api.TagoBusApi
import com.hye.data.remote.dto.bus.common.BusSttnItemDto
import javax.inject.Inject

class TagoBusRemoteDataSourceImpl @Inject constructor(
    private val api: TagoBusApi,
) : BusRemoteDataSource {

    override val priority: Int = 0

    override fun canHandle(
        lat: Double,
        lng: Double
    ) = true

    override suspend fun getNearbyStations(
        lat: Double,
        lng: Double
    ): List<BusSttnItemDto> {

        return api.getNearbyStations(
            latitude = lat,
            longitude = lng
        ).response.body?.items ?: emptyList()
    }


}