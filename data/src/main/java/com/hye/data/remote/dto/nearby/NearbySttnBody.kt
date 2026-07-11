package com.hye.data.remote.dto.nearby

import kotlinx.serialization.Serializable


@Serializable
data class NearbySttnBody(
    val items: NearbySttnItems? = null,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)