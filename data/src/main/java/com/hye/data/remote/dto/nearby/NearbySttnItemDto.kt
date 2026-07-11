package com.hye.data.remote.dto.nearby

import kotlinx.serialization.Serializable

@Serializable
data class NearbySttnItemDto(
    val citycode: Int,          // 도시코드
    val gpsLati: Double,        // 정류소 위도
    val gpsLong: Double,        // 정류소 경도
    val nodeid: String,         // 정류소 고유 ID
    val nodeno: Int? = null,    // 정류소 번호
    val nodenm: String          // 정류소 명
)

