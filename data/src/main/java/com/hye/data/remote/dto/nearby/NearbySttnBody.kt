package com.hye.data.remote.dto.nearby

import kotlinx.serialization.Serializable


@Serializable
data class NearbySttnBody(
    // 빈 문자열 방어용 시리얼라이저 적용
    @Serializable(with = NearbySttnItemsStringOrObjectSerializer::class)
    val items: NearbySttnItems? = null,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)