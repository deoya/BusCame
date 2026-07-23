package com.hye.domain.model.arrival

data class BusArrivalInfo(
    val route: RouteConfig,
    val minutes: Int?,             // 💡 남은 시간 (분 단위) -> Ticker와 알림창에서 쓸 핵심 데이터!
    val remainingStops: Int?,      // 남은 정류장 수 (예: 3)
)