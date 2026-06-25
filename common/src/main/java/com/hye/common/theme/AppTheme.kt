package com.hye.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun AppTheme(
    content:@Composable () -> Unit
){
    val dimens = AppDimens()

    CompositionLocalProvider(
        LocalDimens provides dimens,
        LocalColors provides LightColors
    ) {
        content()
    }
}

object AppTheme{
    val dimens: AppDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}