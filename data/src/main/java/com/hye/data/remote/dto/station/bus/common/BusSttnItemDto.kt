package com.hye.data.remote.dto.station.bus.common

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

// SeoulBus: stationId / stationNm / gpsX / gpsY / arsId
// TAGO    : nodeid    / nodenm    / gpslong / gpslati / (없음, citycode만 있음)
@Serializable
data class BusSttnItemDto(
    val citycode: Int? = null,          // 도시코드
    @JsonNames("nodeid") val stationId: String,         // 정류소 고유 ID
    @JsonNames("nodenm") val stationNm: String,          // 정류소 명
    @JsonNames("gpsY", "gpslati") val gpsLati: Double,        // 정류소 위도
    @JsonNames("gpsX", "gpslong") val gpsLong: Double,        // 정류소 경도
    val arsId: String? = null,
)