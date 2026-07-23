package com.hye.data.datasource.route

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hye.domain.model.arrival.RouteConfig
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RouteLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : RouteLocalDataSource {

    private object Keys {
        val DEPARTURE_ID = stringPreferencesKey("departure_id")
        val DEPARTURE_NAME = stringPreferencesKey("departure_name")
        val DEPARTURE_LAT = stringPreferencesKey("departure_lat")
        val DEPARTURE_LNG = stringPreferencesKey("departure_lng")

        val ARRIVAL_ID = stringPreferencesKey("arrival_id")
        val ARRIVAL_NAME = stringPreferencesKey("arrival_name")
        val ARRIVAL_LAT = stringPreferencesKey("arrival_lat")
        val ARRIVAL_LNG = stringPreferencesKey("arrival_lng")

        val CITY_CODE = intPreferencesKey("city_code")
    }


    override suspend fun saveRouteInfo(
        stop: BusStop,
        mode: SelectionMode
    ) {
        dataStore.edit { prefs ->
            when (mode) {
                SelectionMode.DEPARTURE -> {
                    prefs[Keys.CITY_CODE] = stop.cityCode ?: 0
                    prefs[Keys.DEPARTURE_ID] = stop.nodeId
                    prefs[Keys.DEPARTURE_NAME] = stop.name
                    prefs[Keys.DEPARTURE_LAT] = stop.latitude.toString()
                    prefs[Keys.DEPARTURE_LNG] = stop.longitude.toString()
                }

                SelectionMode.ARRIVAL -> {
                    prefs[Keys.CITY_CODE] = stop.cityCode ?: 0
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
    }

    override suspend fun getSavedRouteInfos(): Pair<BusStop?, BusStop?> {
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

        return Pair(departure, arrival)
    }

    override val savedRouteFlow: Flow<RouteConfig?>
        get() = TODO("Not yet implemented")

}