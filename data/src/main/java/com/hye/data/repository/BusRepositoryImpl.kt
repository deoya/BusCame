package com.hye.data.repository

import com.hye.data.di.IoDispatcher
import com.hye.data.di.qualifier.BusSttnInfoApiKey
import com.hye.data.remote.api.TagoBusApi
import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.BusStop
import com.hye.domain.repository.BusRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    private val api: TagoBusApi,
    @BusSttnInfoApiKey private val apiKey: String,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BusRepository {

    override suspend fun getNearbyBusStops(lat: Double, lng: Double): ResultWrapper<List<BusStop>> {
        return withContext(ioDispatcher) {
            runCatching {
                val response = api.getNearbyStations(
                    serviceKey = apiKey,
                    latitude = lat,
                    longitude = lng
                )
                response.response.body?.items?.item?.map { dto ->
                    BusStop(
                        nodeId = dto.nodeid,
                        name = dto.nodenm,
                        latitude = dto.gpsLati,
                        longitude = dto.gpsLong
                    )
                } ?: emptyList()
            }.fold(
                onSuccess = { data ->
                    ResultWrapper.Success(data)
                },
                onFailure = { error ->
                    if (error is CancellationException) throw error
                    ResultWrapper.Error(error)
                }
            )
        }
    }
}