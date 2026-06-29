package com.hye.common.mock

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DirectionsRun
import androidx.compose.material.icons.outlined.DirectionsWalk
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// ─── 색상 팔레트 ────────────────────────────────────────────────────────────
object BusWasseColors {
    val Green50 = Color(0xFFE8F5E9)
    val Green400 = Color(0xFF66BB6A)
    val Green600 = Color(0xFF2E7D32)
    val Blue500 = Color(0xFF1976D2)
    val Gray50 = Color(0xFFF8F9FA)
    val Gray100 = Color(0xFFF1F3F4)
    val Gray200 = Color(0xFFE8EAED)
    val Gray400 = Color(0xFF9AA0A6)
    val Gray700 = Color(0xFF5F6368)
    val Gray900 = Color(0xFF202124)
    val White = Color(0xFFFFFFFF)
    val Surface = Color(0xFFF5F5F5)
    val ErrorBg = Color(0xFFFFEBEE)
    val ErrorText = Color(0xFFC62828)
}

// ─── 샘플 데이터 / 미리보기 ───────────────────────────────────────────────
fun sampleBusInfo() = BusArrivalInfo(
    routeNumber = "160",
    direction = "양재역 방면",
    arrivalMinutes = 2,
    arrivalTime = "09:43",
    nextBusMinutes = 11,
    nextBusRoute = "472",
    walkMinutes = 4,
    walkDistanceMeters = 280,
    spareSeconds = 90,
    isRealtime = true
)


data class BusArrivalInfo(
    val routeNumber: String,
    val direction: String,
    val arrivalMinutes: Int,
    val arrivalTime: String,
    val nextBusMinutes: Int,
    val nextBusRoute: String,
    val walkMinutes: Int,
    val walkDistanceMeters: Int,
    val spareSeconds: Int,
    val isRealtime: Boolean = true
) {
    val boardingStatus: BoardingStatus
        get() {
            val spare = (arrivalMinutes * 60) - (walkMinutes * 60)
            return when {
                spare >= 90 -> BoardingStatus.CAN_WALK
                spare >= 0 -> BoardingStatus.CAN_RUN
                else -> BoardingStatus.MISSED
            }
        }
}


data class RouteConfig(
    val departureStop: String = "강남역 10번 출구",
    val arrivalStop: String = "여의도역"
)

enum class BoardingStatus(
    val bgColor: Color,
    val iconTint: Color,
    val icon: ImageVector,
    val mainText: String,
    val subText: String
) {
    CAN_RUN(
        Color(0xFFF1F9F1),
        Color(0xFF1B873F),
        Icons.Outlined.DirectionsRun,
        "지금 뛰면 탈 수 있어요!",
        "서두르면 여유 있게 탑승할 수 있어요."
    ),
    CAN_WALK(
        Color(0xFFF1F9F1),
        Color(0xFF1B873F),
        Icons.Outlined.DirectionsWalk,
        "걸어도 탈 수 있어요!",
        "여유롭게 출발해도 괜찮아요."
    ),
    MISSED(
        Color.Red,//BusWasseColors.ErrorBg,
        Color.Red,//BusWasseColors.ErrorText,
        Icons.Outlined.Close,
        "이번 버스는 놓쳤어요.",
        "다음 버스를 확인해 주세요."
    )
}