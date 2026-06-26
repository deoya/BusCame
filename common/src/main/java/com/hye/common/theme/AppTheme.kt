package com.hye.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val materialLightColorScheme = lightColorScheme(
    primary = LightColors.primary,
    onPrimary = LightColors.onPrimary,
    error = LightColors.error,
    background = LightColors.background,
    surface = LightColors.surface,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val dimens = AppDimens()

    CompositionLocalProvider(
        LocalDimens provides dimens,
        LocalColors provides LightColors
    ) {
        MaterialTheme(
            colorScheme = materialLightColorScheme,
            content = content
        )
    }
}

object AppTheme {
    val dimens: AppDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}