package com.hye.common.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val materialLightColorScheme = lightColorScheme(
    primary = LightCoreColors.primary,
    onPrimary = LightCoreColors.onPrimary,
    error = LightCoreColors.error,
    background = LightCoreColors.background,
    surface = LightCoreColors.surface,
)

@Composable
fun DesignTheme(
    content: @Composable () -> Unit
) {
    val dimens = CoreDimens()

    CompositionLocalProvider(
        LocalDimens provides dimens,
        LocalCoreColors provides LightCoreColors
    ) {
        MaterialTheme(
            colorScheme = materialLightColorScheme,
            content = content
        )
    }
}

object DesignTheme {
    val dimens: CoreDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current

    val colors: CoreColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCoreColors.current
}