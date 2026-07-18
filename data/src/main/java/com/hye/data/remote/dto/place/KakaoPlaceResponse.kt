package com.hye.data.remote.dto.place

import kotlinx.serialization.Serializable

@Serializable
data class KakaoPlaceResponse(
    val documents: List<KakaoPlaceDocument>
)