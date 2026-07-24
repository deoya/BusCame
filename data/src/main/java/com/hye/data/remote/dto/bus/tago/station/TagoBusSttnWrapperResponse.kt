package com.hye.data.remote.dto.bus.tago.station

import kotlinx.serialization.Serializable

@Serializable
data class TagoBusSttnWrapperResponse(
    val response: TagoBusSttnResponse
)