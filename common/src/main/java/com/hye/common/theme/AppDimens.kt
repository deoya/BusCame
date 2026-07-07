package com.hye.common.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimens(
    val mapPlaceholderHeight: Dp = 90.dp,
)

// Design System 전용 LocalProvider
val LocalFeatureDimens = staticCompositionLocalOf { AppDimens() }
