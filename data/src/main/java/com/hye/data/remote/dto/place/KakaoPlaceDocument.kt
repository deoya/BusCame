package com.hye.data.remote.dto.place

import kotlinx.serialization.Serializable

@Serializable
data class KakaoPlaceDocument(
    val place_name: String,   // 장소 이름 (예: 강남역 2호선)
    val address_name: String, // 지번 주소
    val x: String,            // 경도 (Longitude) - 주의: String으로 내려옵니다!
    val y: String             // 위도 (Latitude)
)