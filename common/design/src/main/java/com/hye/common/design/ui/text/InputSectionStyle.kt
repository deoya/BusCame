package com.hye.common.design.ui.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.hye.common.design.theme.DesignTheme


@Composable
fun StyledInputSection(
    label: @Composable () -> Unit = {},
    text: @Composable () -> Unit,
    space: Dp = DesignTheme.dimens.spaceXs,
    description: @Composable () -> Unit = {}
) {
    Column(verticalArrangement = Arrangement.spacedBy(space)) {
        label()
        text()
        description()
    }
}