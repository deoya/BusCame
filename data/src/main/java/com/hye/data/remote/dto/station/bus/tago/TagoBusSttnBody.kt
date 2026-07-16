package com.hye.data.remote.dto.station.bus.tago

import com.hye.data.remote.dto.station.bus.common.BusSttnItemDto
import kotlinx.serialization.Serializable

@Serializable
data class TagoBusSttnBody(
    @Serializable(with = TagoBusSttnListSerializer::class)
    val items: List<BusSttnItemDto>? = null,
)