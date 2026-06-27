package com.hye.common.design.ui.chip

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.hye.common.design.theme.DesignTheme

@Composable
fun StyledFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    selectedContainerColor: Color = DesignTheme.colors.info,
    selectedLabelColor: Color = Color.White,
    containerColor: Color = DesignTheme.colors.surfaceVariant,
    labelColor: Color = DesignTheme.colors.onSurfaceVariant,
    borderEnabled: Boolean = true,
    borderSelected: Boolean,
    borderColor: Color = Color.Transparent,
    borderSelectedColor: Color = Color.Transparent,
    shape: Dp = DesignTheme.dimens.radiusMd,
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(label, fontWeight = FontWeight.Medium) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = selectedContainerColor,
            selectedLabelColor = selectedLabelColor,
            containerColor = containerColor,
            labelColor = labelColor
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = borderEnabled,
            selected = borderSelected,
            borderColor = borderColor,
            selectedBorderColor = borderSelectedColor
        ),
        shape = RoundedCornerShape(shape)
    )
}