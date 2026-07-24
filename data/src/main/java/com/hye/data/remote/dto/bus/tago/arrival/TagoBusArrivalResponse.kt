package com.hye.data.remote.dto.bus.tago.arrival

import com.hye.data.remote.dto.bus.common.CommonBusHeader
import kotlinx.serialization.Serializable


@Serializable
data class TagoBusArrivalResponse(
    val header: CommonBusHeader,
    val body: TagoBusArrivalBody? = null
)