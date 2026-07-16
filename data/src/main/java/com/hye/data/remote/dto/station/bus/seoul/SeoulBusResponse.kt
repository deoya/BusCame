package com.hye.data.remote.dto.station.bus.seoul

import com.hye.data.remote.dto.station.bus.common.BusSttnHeader
import kotlinx.serialization.Serializable

@Serializable
data class SeoulBusResponse(
    val msgHeader: BusSttnHeader,
    val msgBody: SeoulBusBody? = null
)