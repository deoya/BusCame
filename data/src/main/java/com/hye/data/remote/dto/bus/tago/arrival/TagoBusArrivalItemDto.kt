package com.hye.data.remote.dto.bus.tago.arrival

import kotlinx.serialization.Serializable

@Serializable
data class TagoBusArrivalItemDto(
    val routeid: String,             // 노선 ID
    val routeno: String,             // 노선 번호
    val nodeid: String,              // 정류소 ID

    val arrtime: Int? = null,              // 도착예정시간 (초 단위)
    val arrprevstationcnt: Int? = null     // 남은 정류소 수
)