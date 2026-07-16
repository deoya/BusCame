package com.hye.data.datasource.tago

import com.hye.data.datasource.BusRemoteDataSource
import com.hye.data.di.qualifier.TagoBusSttnApiKey
import com.hye.data.remote.api.TagoBusApi
import com.hye.data.remote.dto.station.bus.common.BusSttnItemDto
import javax.inject.Inject

class TagoBusRemoteDataSourceImpl @Inject constructor(
    private val api: TagoBusApi,
    @TagoBusSttnApiKey private val apiKey: String
) : BusRemoteDataSource {

    override fun canHandle(
        lat: Double,
        lng: Double
    ) = true

    override suspend fun getNearbyStations(
        lat: Double,
        lng: Double
    ): List<BusSttnItemDto> {

        return api.getNearbyStations(
            serviceKey = apiKey,
            latitude = lat,
            longitude = lng
        ).response.body?.items ?: emptyList()
    }
}