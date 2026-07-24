package com.hye.data.remote.dto.bus.seoul

import com.hye.data.remote.dto.bus.common.CommonBusHeader
import kotlinx.serialization.Serializable

@Serializable
data class SeoulBusArrivalResponse(
    val msgHeader: CommonBusHeader,
    val msgBody: SeoulBusArrivalBody? = null
)