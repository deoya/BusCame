package com.hye.data.remote.dto.bus.tago.arrival

import kotlinx.serialization.Serializable

@Serializable
data class TagoBusArrivalBody(
    @Serializable(with = TagoBusArrivalListSerializer::class)
    val items: List<TagoBusArrivalItemDto>? = null
)