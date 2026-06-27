package com.hye.common.design.ui.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme

private object AlertTokens {
    val IconTopOffset = 2.dp
}

@Composable
fun StyledAlert(
    content: @Composable () -> Unit,
    needIcon: Boolean = true,
    color: Color = DesignTheme.colors.surfaceVariant
) {
    Surface(
        color = color,
        shape = RoundedCornerShape(DesignTheme.dimens.radiusMd),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(DesignTheme.dimens.spaceMd),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXs)
        ) {
            if (needIcon) Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = DesignTheme.colors.onSurfaceVariant,
                modifier = Modifier
                    .size(DesignTheme.dimens.iconSmall)
                    .padding(top = AlertTokens.IconTopOffset)
            )
            content()
        }
    }
}