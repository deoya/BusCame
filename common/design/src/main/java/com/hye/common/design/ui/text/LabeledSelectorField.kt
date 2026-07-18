package com.hye.common.design.ui.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.hye.common.design.theme.DesignTheme

@Composable
fun LabeledSelectorField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.ChevronRight,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXxs)
    ) {
        LabelText(
            text = label,
            style = TextStyleSize.Small,
            color = DesignTheme.colors.onSurfaceVariant
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = DesignTheme.colors.surfaceVariant,
                    shape = RoundedCornerShape(DesignTheme.dimens.radiusSm)
                )
                .border(
                    width = DesignTheme.dimens.strokeThin,
                    color = DesignTheme.colors.border,
                    shape = RoundedCornerShape(DesignTheme.dimens.radiusSm)
                )
                .clickable(onClick = onClick)
                .padding(
                    horizontal = DesignTheme.dimens.spaceS,
                    vertical = DesignTheme.dimens.spaceXs
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LabelText(
                text = value,
                style = TextStyleSize.Medium,
                color = DesignTheme.colors.onBackground,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = icon,
                contentDescription = "선택",
                tint = DesignTheme.colors.onSurfaceVariant,
                modifier = Modifier.size(DesignTheme.dimens.iconSmall)
            )
        }
    }
}
