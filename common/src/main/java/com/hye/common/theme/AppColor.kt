package com.hye.common.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CategoryColor(
    val main: Color,
    val container: Color
)

data class FeatureColors(
    val categoryColors: CategoryColor
)

val LightFeatureColors = FeatureColors(
    categoryColors = CategoryColor(main = Color(0xFF488AFF), container = Color(0xFFD9E4FF)),
)

// Feature 전용 LocalProvider
val LocalFeatureColors = staticCompositionLocalOf { LightFeatureColors }