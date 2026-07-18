package com.hye.data.remote.dto.station.bus.tago

import com.hye.data.remote.dto.station.bus.common.BusSttnHeader
import kotlinx.serialization.Serializable

@Serializable
data class TagoBusSttnResponse(
    val header: BusSttnHeader,
    val body: TagoBusSttnBody? = null
)