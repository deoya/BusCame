package com.hye.common.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CategoryColor(
    val main: Color,
    val container: Color
)

data class FeatureColors(
    val categoryColors: CategoryColor,
    val markerFinColor: Color,
    val nextBusColor: Color
)

val LightFeatureColors = FeatureColors(
    categoryColors = CategoryColor(main = Color(0xFF488AFF), container = Color(0xFFD9E4FF)),
    markerFinColor = Color(0xFFFF5A4C),
    nextBusColor = Color(0xFF8A8A8A)
)

// Feature 전용 LocalProvider
val LocalFeatureColors = staticCompositionLocalOf { LightFeatureColors }