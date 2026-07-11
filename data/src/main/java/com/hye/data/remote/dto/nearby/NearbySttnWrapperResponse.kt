package com.hye.data.remote.dto.nearby

import com.hye.data.remote.dto.NearbySttnResponse
import kotlinx.serialization.Serializable

@Serializable
data class NearbySttnWrapperResponse(
    val response: NearbySttnResponse
)

