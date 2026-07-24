package com.hye.data.remote.dto.bus.tago.station

import com.hye.data.remote.dto.bus.common.BusSttnItemDto
import kotlinx.serialization.Serializable

@Serializable
data class TagoBusSttnItems(
    val item: List<BusSttnItemDto> = emptyList()
)