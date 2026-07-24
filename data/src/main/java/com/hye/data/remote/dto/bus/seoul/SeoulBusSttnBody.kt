package com.hye.data.remote.dto.bus.seoul

import com.hye.data.remote.dto.bus.common.BusSttnItemDto
import kotlinx.serialization.Serializable

@Serializable
data class SeoulBusSttnBody(
    val itemList: List<BusSttnItemDto> = emptyList()
)
