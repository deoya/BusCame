package com.hye.data.remote.dto.station.bus.tago

import com.hye.data.remote.dto.station.bus.common.BusSttnItemDto
import kotlinx.serialization.Serializable

@Serializable
data class TagoBusSttnItems(
    val item: List<BusSttnItemDto> = emptyList()
)