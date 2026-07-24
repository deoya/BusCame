package com.hye.data.repository

import com.hye.data.datasource.place.PlaceRemoteDataSource
import com.hye.data.di.qualifier.IoDispatcher
import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.Place
import com.hye.domain.repository.PlaceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class PlaceRepositoryImpl @Inject constructor(
    private val dataSource: PlaceRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PlaceRepository {

    override suspend fun searchPlaces(keyword: String): ResultWrapper<List<Place>> {
        return withContext(ioDispatcher) {
            runCatching {
                dataSource.searchPlaces(keyword)
                    .map { dto ->
                        Place(
                            name = dto.place_name,
                            address = dto.address_name,
                            latitude = dto.y.toDouble(),
                            longitude = dto.x.toDouble()
                        )
                    }
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