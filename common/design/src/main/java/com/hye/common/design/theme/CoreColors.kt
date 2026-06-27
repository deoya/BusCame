package com.hye.common.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity

data class CoreColors(
    // 기본 테마 색상
    val primary: Color,
    val primaryContainer: Color, // 선택된 항목의 배경 등 강조가 필요한 곳에 사용
    val onPrimary: Color,
    val info: Color,
    val infoContainer: Color,
    val success: Color,
    val error: Color,
    // 배경 및 표면 색상
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val onBackground: Color,
    val onSurfaceVariant: Color,
    // 상태 색상
    val disabled: Color,// 비활성화된 아이콘, 텍스트 등에 사용
    val disabledContainer: Color,
    val scrim: Color, // 모달, 다이얼로그 뒷배경 딤처리용
    val divider: Color,// 구분선 색상
    //테두리
    val border: Color,
    val borderSubtle: Color,
    // 그 외 UI
    val badgeContainer: Color,
    val onBadge: Color,

    )

val LightCoreColors = CoreColors(
    primary = Color(0xFF5174FF),
    primaryContainer = Color(0xFFE8EFFF),
    onPrimary = Color.White,
    info = Color(0xFF3B82F6),
    infoContainer = Color(0xFFEFF6FF),
    success = Color(0xFF10B981),
    error = Color(0xFFFF7A6E),

    background = Color.White,
    surface = Color.White,
    surfaceVariant = Color(0xFFF8FAFC),
    onBackground = Color(0xFF1E293B),
    onSurfaceVariant = Color(0xFF64748B),

    disabled = Color(0xFFE7E7E7),
    disabledContainer = Color(0xFFE2E8F0),
    scrim = Color(0x66000000),

    divider = Color(0xFFEEEEEE),
    border = Color.LightGray,
    borderSubtle = Color(0xFFF1F5F9),

    badgeContainer = Color(0xFFF8FAFC),
    onBadge = Color(0xFF64748B)
)

val Color.alphaPrimary
    @Composable
    @ReadOnlyComposable
    get() = with(LocalDensity.current) { this@alphaPrimary.copy(alpha = DesignTheme.dimens.alphaPrimary) }


// Design System 전용 LocalProvider
val LocalCoreColors = staticCompositionLocalOf { LightCoreColors }
