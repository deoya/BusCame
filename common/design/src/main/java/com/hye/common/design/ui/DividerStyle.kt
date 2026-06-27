package com.hye.common.design.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.hye.common.design.theme.DesignTheme


@Composable
fun StyledDivider(
    color: Color = DesignTheme.colors.divider,
    thickness: Dp = DesignTheme.dimens.strokeThin,
    padding: Dp = DesignTheme.dimens.spaceMd,
    modifier: Modifier = Modifier
) = Divider(
    color = color,
    thickness = thickness,
    modifier = modifier.padding(vertical = padding)
)