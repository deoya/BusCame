package com.hye.domain.model.map

data class BusStop(
    val nodeId: String,      // 정류장 고유 노드 ID (실제 API 통신 시 필수)
    val arsId: String,       // 표지판에 적힌 ARS 번호 (예: "01-234", 사용자에게 보여줄 때 유용)
    val name: String,        // 정류장 이름 (예: "강남역.강남교보타워")
    val direction: String,   // 방면 (예: "신논현역 방면" - 헷갈리지 않게 표기)
    val latitude: Double,    // 위도 (지도에 핀을 띄우기 위해 필요)
    val longitude: Double    // 경도
)
