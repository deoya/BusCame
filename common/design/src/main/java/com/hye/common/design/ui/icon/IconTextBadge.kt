package com.hye.common.design.ui.icon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.hye.common.design.theme.DesignTheme

@Composable
fun IconTextBadge(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = DesignTheme.colors.badgeContainer,
    contentColor: Color = DesignTheme.colors.onBadge
) {
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(DesignTheme.dimens.radiusSm),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = DesignTheme.dimens.spaceXs,
                vertical = DesignTheme.dimens.spaceXxs
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXxs)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(DesignTheme.dimens.iconSmall)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}