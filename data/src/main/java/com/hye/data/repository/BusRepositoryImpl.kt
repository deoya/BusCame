package com.hye.data.repository

import com.hye.data.datasource.bus.BusRemoteDataSource
import com.hye.data.datasource.route.RouteLocalDataSource
import com.hye.data.di.qualifier.IoDispatcher
import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode
import com.hye.domain.repository.BusRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class BusRepositoryImpl @Inject constructor(
    private val busDataSources: Set<@JvmSuppressWildcards BusRemoteDataSource>,
    private val routeDataSource: RouteLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BusRepository {


    override suspend fun getNearbyBusStops(lat: Double, lng: Double): ResultWrapper<List<BusStop>> {
        return withContext(ioDispatcher) {
            runCatching {

                val dataSource = busDataSources
                    .filter {
                        it.canHandle(
                            lat,
                            lng
                        )
                    }
                    .maxByOrNull { it.priority }  // 우선순위 우선으로 api 호출
                    ?: throw IllegalStateException("해당 지역을 처리할 수 있는 데이터 소스가 없습니다.")

                dataSource.getNearbyStations(lat, lng).map { dto ->
                    BusStop(
                        cityCode = dto.cityCode,
                        nodeId = dto.stationId,
                        name = dto.stationNm,
                        latitude = dto.gpsLati,
                        longitude = dto.gpsLong,
                    )
                }
            }.fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { error ->
                    if (error is CancellationException) throw error
                    ResultWrapper.Error(error)
                }
            )
        }
    }

    override suspend fun saveBusStopInfo(stop: BusStop, mode: SelectionMode): ResultWrapper<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                routeDataSource.saveRouteInfo(stop, mode)
            }.fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { error ->
                    if (error is CancellationException) throw error
                    ResultWrapper.Error(error)
                }
            )
        }
    }

    override suspend fun getSavedRouteInfos(): ResultWrapper<Pair<BusStop?, BusStop?>> {
        return withContext(ioDispatcher) {
            runCatching {
                routeDataSource.getSavedRouteInfos()
            }.fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { error ->
                    if (error is CancellationException) throw error
                    ResultWrapper.Error(error)
                }
            )
        }
    }
}