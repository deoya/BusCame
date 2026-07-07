package com.hye.common.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. 순수 디자인 시스템 수치 (Core)


data class CoreDimens(
    // 🟢 Spacing & Padding (여백 및 간격)
    val spaceXxxs: Dp = 2.dp,
    val spaceXxs: Dp = 4.dp,
    val spaceXs: Dp = 8.dp,
    val spaceS: Dp = 10.dp,
    val spaceSm: Dp = 12.dp,
    val spaceMd: Dp = 16.dp,  // 표준 여백
    val spaceLg: Dp = 20.dp,
    val spaceXl: Dp = 24.dp,
    val spaceXxl: Dp = 32.dp,

    // 🟢 Radius (모서리 둥글기 - 컴포넌트 형태 결정)
    val radiusSm: Dp = 8.dp,
    val radiusMd: Dp = 12.dp,
    val radiusLg: Dp = 16.dp,
    val radiusL: Dp = 20.dp,
    val radiusXl: Dp = 24.dp, // 기존 카드의 둥글기
    val radiusRound: Dp = 999.dp, // 완전한 원형/알약 형태용

    // 🟢 Component Size (공통 UI 요소 크기)
    val iconXSmall: Dp = 12.dp,
    val iconSmall: Dp = 16.dp,
    val iconMediumSmall: Dp = 18.dp,
    val iconMedium: Dp = 24.dp, // 일반적인 아이콘 크기
    val iconLarge: Dp = 32.dp,
    val thumbNormal: Dp = 64.dp, // 기존 thumbSize

    val buttonHeight: Dp = 50.dp, // 기존 bigBtn
    val bottomBarHeight: Dp = 60.dp,

    // 🟢 Text Size (폰트 크기)
    val textSizeXxxs: TextUnit = 10.sp,
    val textSizeXxs: TextUnit = 11.sp,
    val textSizeXs: TextUnit = 12.sp,
    val textSizeS: TextUnit = 13.sp,
    val textSizeM: TextUnit = 15.sp,
    val textSizeL: TextUnit = 18.sp,
    val textSizeXl: TextUnit = 20.sp,

    // 🟢 Elevation & Stroke (그림자 및 테두리 두께)
    val strokeThin: Dp = 1.dp,
    val strokeNormal: Dp = 1.5.dp,

    val elevationNone: Dp = 0.dp,
    val elevationXs: Dp = 2.dp,
    val elevationSm: Dp = 4.dp,
    val elevationMd: Dp = 8.dp,

    // 🟢 Alpha & Ratios (비율 및 투명도)
    val alphaMuted: Float = 0.2f,
    val alphaPrimary: Float = 0.8f
)

val Dp.toSp
    @Composable
    @ReadOnlyComposable
    get() = with(LocalDensity.current) { this@toSp.toSp() }
val Dp.toPx
    @Composable
    @ReadOnlyComposable
    get() = with(LocalDensity.current) { this@toPx.toPx() }

@Composable
@ReadOnlyComposable
private fun Int.toDp(density: Density) = with(density) { this@toDp.toDp() }

val LocalDimens = staticCompositionLocalOf {
    CoreDimens()
}
