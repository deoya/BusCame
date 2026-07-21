package com.hye.domain.model.arrival

data class BusArrivalInfo(
    val routeId: String,          // 버스 노선 고유 ID (예: "100100112")
    val routeName: String,        // 사용자에게 보여줄 버스 번호 (예: "144", "M4102")
    val minutes: Int,             // 💡 남은 시간 (분 단위) -> Ticker와 알림창에서 쓸 핵심 데이터!
    val remainingStops: Int,      // 남은 정류장 수 (예: 3)
)