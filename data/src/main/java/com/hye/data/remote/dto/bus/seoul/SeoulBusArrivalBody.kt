package com.hye.data.remote.dto.bus.seoul

import kotlinx.serialization.Serializable

@Serializable
data class SeoulBusArrivalBody(
    val itemList: List<BusArrivalItemDto> = emptyList()
)