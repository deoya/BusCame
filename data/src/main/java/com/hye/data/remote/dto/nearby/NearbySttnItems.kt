package com.hye.data.remote.dto.nearby

import kotlinx.serialization.Serializable

@Serializable
data class NearbySttnItems(
    // item이 1개일 때를 방어하기 위해 커스텀 시리얼라이저
    @Serializable(with = NearbySttnListSerializer::class)
    val item: List<NearbySttnItemDto> = emptyList()
)