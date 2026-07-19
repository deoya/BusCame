package com.hye.common.design.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.BodyText
import com.hye.common.design.ui.text.TextStyleSize

@Composable
fun <T> ToggleButton(
    options: List<T>,
    selected: T,
    onSelect: (T) -> Unit,
    label: (T) -> String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(DesignTheme.dimens.spaceS))
            .background(DesignTheme.colors.surface)
            .padding(DesignTheme.dimens.spaceTiny)
    ) {
        options.forEach { option ->
            val isSelected = option == selected
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(DesignTheme.dimens.spaceXs))
                    .background(if (isSelected) DesignTheme.colors.primary else Color.Transparent)
                    .clickable { onSelect(option) }
                    .padding(
                        horizontal = DesignTheme.dimens.spaceSemiMd,
                        vertical = DesignTheme.dimens.spaceMicro
                    )
            ) {
                BodyText(
                    text = label(option),
                    style = TextStyleSize.Medium,
                    fontWeight = FontWeight.Medium,
                    color = if (isSelected) DesignTheme.colors.onPrimary else DesignTheme.colors.onSurfaceVariant
                )
            }
        }
    }
}