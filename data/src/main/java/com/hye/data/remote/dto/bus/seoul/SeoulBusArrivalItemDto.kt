package com.hye.data.remote.dto.bus.seoul

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class SeoulBusArrivalItemDto(
    @JsonNames("busRouteId") val routeId: String,  // 노선 ID
    @JsonNames("rtNm") val routeName: String,      // 노선명
    @JsonNames("stId") val stationId: String,      // 정류소 ID

    // 도착 정보 (첫 번째 도착 예정 버스)
    val arrmsg1: String? = null,    // 예: "3분40초후[2번째 전]"
    val arrmsgSec1: Int? = null,    // 예: 220 (초 단위)

    // 도착 정보 (두 번째 도착 예정 버스 - 필요 시 사용)
    val arrmsg2: String? = null,
    val arrmsgSec2: Int? = null
)