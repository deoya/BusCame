package com.hye.data.remote.dto.station.bus.seoul

import com.hye.data.remote.dto.station.bus.common.BusSttnItemDto
import kotlinx.serialization.Serializable

@Serializable
data class SeoulBusBody(
    val itemList: List<BusSttnItemDto> = emptyList()
)
