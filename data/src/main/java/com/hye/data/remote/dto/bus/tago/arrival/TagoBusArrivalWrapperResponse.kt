package com.hye.data.remote.dto.bus.tago.arrival

import kotlinx.serialization.Serializable

@Serializable
data class TagoBusArrivalWrapperResponse(
    val response: TagoBusArrivalResponse
)