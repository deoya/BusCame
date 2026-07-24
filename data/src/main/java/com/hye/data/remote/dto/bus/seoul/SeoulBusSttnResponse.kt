package com.hye.data.remote.dto.bus.seoul

import com.hye.data.remote.dto.bus.common.CommonBusHeader
import kotlinx.serialization.Serializable

@Serializable
data class SeoulBusSttnResponse(
    val msgHeader: CommonBusHeader,
    val msgBody: SeoulBusSttnBody? = null
)