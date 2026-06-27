package com.hye.common.design.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hye.common.design.theme.DesignTheme

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    containerColor: Color = DesignTheme.colors.info,
    content: @Composable () -> Unit
) {
    Surface(
        color = containerColor,
        shape = RoundedCornerShape(DesignTheme.dimens.radiusLg)
    ) {
        Row(
            modifier = modifier.padding(
                horizontal = DesignTheme.dimens.spaceSm,
                vertical = DesignTheme.dimens.spaceXxs
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXxs)
        ) {
            content()
        }
    }
}