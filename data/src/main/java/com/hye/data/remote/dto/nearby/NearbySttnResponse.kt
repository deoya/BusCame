package com.hye.data.remote.dto.nearby

import com.hye.data.remote.dto.common.TagoHeader
import kotlinx.serialization.Serializable

@Serializable
data class NearbySttnResponse(
    val header: TagoHeader,
    val body: NearbySttnBody? = null
)
