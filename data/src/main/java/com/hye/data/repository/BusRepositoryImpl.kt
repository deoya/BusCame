package com.hye.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hye.data.datasource.BusRemoteDataSource
import com.hye.data.di.qualifier.IoDispatcher
import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode
import com.hye.domain.repository.BusRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class BusRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val dataSources: Set<@JvmSuppressWildcards BusRemoteDataSource>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BusRepository {

    private object Keys {
        val DEPARTURE_ID = stringPreferencesKey("departure_id")
        val DEPARTURE_NAME = stringPreferencesKey("departure_name")
        val DEPARTURE_LAT = stringPreferencesKey("departure_lat")
        val DEPARTURE_LNG = stringPreferencesKey("departure_lng")

        val ARRIVAL_ID = stringPreferencesKey("arrival_id")
        val ARRIVAL_NAME = stringPreferencesKey("arrival_name")
        val ARRIVAL_LAT = stringPreferencesKey("arrival_lat")
        val ARRIVAL_LNG = stringPreferencesKey("arrival_lng")
    }


    override suspend fun getNearbyBusStops(lat: Double, lng: Double): ResultWrapper<List<BusStop>> {
        return withContext(ioDispatcher) {
            runCatching {

                val dataSource = dataSources
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
                        nodeId = dto.stationId,
                        name = dto.stationNm,
                        latitude = dto.gpsLati,
                        longitude = dto.gpsLong
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

    override suspend fun saveRouteStop(stop: BusStop, mode: SelectionMode): ResultWrapper<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                dataStore.edit { prefs ->
                    when (mode) {
                        SelectionMode.DEPARTURE -> {
                            prefs[Keys.DEPARTURE_ID] = stop.nodeId
                            prefs[Keys.DEPARTURE_NAME] = stop.name
                            prefs[Keys.DEPARTURE_LAT] = stop.latitude.toString()
                            prefs[Keys.DEPARTURE_LNG] = stop.longitude.toString()
                        }

                        SelectionMode.ARRIVAL -> {
                            prefs[Keys.ARRIVAL_ID] = stop.nodeId
                            prefs[Keys.ARRIVAL_NAME] = stop.name
                            prefs[Keys.ARRIVAL_LAT] = stop.latitude.toString()
                            prefs[Keys.ARRIVAL_LNG] = stop.longitude.toString()
                        }

                        SelectionMode.NONE -> {
                            throw IllegalArgumentException("NONE 모드에서는 정류장을 저장할 수 없습니다.")
                        }
                    }
                }
                Unit
            }.fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { error ->
                    if (error is CancellationException) throw error
                    ResultWrapper.Error(error)
                }
            )
        }
    }

    override suspend fun getSavedRouteStops(): ResultWrapper<Pair<BusStop?, BusStop?>> {
        return withContext(ioDispatcher) {
            runCatching {
                val preferences = dataStore.data.first() // DataStore에서 스냅샷 1회 조회

                // 1. 출발지 정보 복원
                val depId = preferences[Keys.DEPARTURE_ID]
                val departure = if (!depId.isNullOrBlank()) {
                    BusStop(
                        nodeId = depId,
                        name = preferences[Keys.DEPARTURE_NAME] ?: "",
                        latitude = preferences[Keys.DEPARTURE_LAT]?.toDoubleOrNull() ?: 0.0,
                        longitude = preferences[Keys.DEPARTURE_LNG]?.toDoubleOrNull() ?: 0.0
                    )
                } else null

                // 2. 도착지 정보 복원
                val arrId = preferences[Keys.ARRIVAL_ID]
                val arrival = if (!arrId.isNullOrBlank()) {
                    BusStop(
                        nodeId = arrId,
                        name = preferences[Keys.ARRIVAL_NAME] ?: "",
                        latitude = preferences[Keys.ARRIVAL_LAT]?.toDoubleOrNull() ?: 0.0,
                        longitude = preferences[Keys.ARRIVAL_LNG]?.toDoubleOrNull() ?: 0.0
                    )
                } else null

                Pair(departure, arrival)
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