package com.hye.data.remote.dto.bus.tago.station

import com.hye.data.remote.dto.bus.common.CommonBusHeader
import kotlinx.serialization.Serializable

@Serializable
data class TagoBusSttnResponse(
    val header: CommonBusHeader,
    val body: TagoBusSttnBody? = null
)