package com.hye.data.remote.dto.bus.common

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

// SeoulBus: headerCd / headerMsg
// TAGO    : resultCode / resultMsg
@Serializable
data class CommonBusHeader(
    @JsonNames("headerCd") val resultCode: String,
    @JsonNames("headerMsg") val resultMsg: String
) {
    val isSuccess: Boolean get() = resultCode == "0" || resultMsg == "00"
}