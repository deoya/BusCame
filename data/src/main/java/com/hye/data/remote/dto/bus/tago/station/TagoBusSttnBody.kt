package com.hye.data.remote.dto.bus.tago.station

import com.hye.data.remote.dto.bus.common.BusSttnItemDto
import kotlinx.serialization.Serializable

@Serializable
data class TagoBusSttnBody(
    @Serializable(with = TagoBusSttnListSerializer::class)
    val items: List<BusSttnItemDto>? = null,
)