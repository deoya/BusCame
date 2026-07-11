package com.hye.data.remote.dto.common

import kotlinx.serialization.Serializable

@Serializable
data class TagoHeader(
    val resultCode: String,
    val resultMsg: String
)