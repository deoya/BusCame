package com.hye.common.design.ui.booleans.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.hye.common.design.theme.DesignTheme

val Boolean.optionColor: Color
    @ReadOnlyComposable
    @Composable
    get() = if (this) DesignTheme.colors.info else DesignTheme.colors.disabled