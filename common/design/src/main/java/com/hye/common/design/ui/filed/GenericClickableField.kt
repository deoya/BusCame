package com.hye.common.design.ui.filed

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.LabelText
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.design.ui.text.TitleText

private object GenericFieldTokens {
    val DefaultIconSize = 13.dp
}

@Preview
@Composable
fun GenericClickableField_preview() {
    GenericClickableField(
        label = "Label",
        value = "Value",
        onClick = {}
    )
}

@Composable
fun GenericClickableField(
    label: String,
    value: String,
    icon: ImageVector? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    // 🎨 컬러 커스텀 슬롯 (기본값은 디자인 시스템의 표준 테마 사용)
    containerColor: Color = DesignTheme.colors.surface,
    borderColor: Color = DesignTheme.colors.borderSubtle,
    iconColor: Color = DesignTheme.colors.onSurfaceVariant,
    labelColor: Color = DesignTheme.colors.onSurfaceVariant,
    valueColor: Color = DesignTheme.colors.onBackground,
    // 📐 수치 커스텀 슬롯
    shape: Shape = RoundedCornerShape(DesignTheme.dimens.radiusSm),
    borderWidth: Dp = DesignTheme.dimens.strokeThin,
    iconSize: Dp = GenericFieldTokens.DefaultIconSize,
    contentPadding: Dp = DesignTheme.dimens.spaceSm,
    spacing: Dp = DesignTheme.dimens.spaceXxs
) {
    Column(
        modifier = modifier
            .background(containerColor, shape)
            .border(borderWidth, borderColor, shape)
            .clickable(onClick = onClick)
            .padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXxs)
        ) {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize),
                    tint = iconColor
                )
            }
            LabelText(
                text = label,
                style = TextStyleSize.Small,
                color = labelColor,
                modifier = Modifier.padding(bottom = 0.dp)
            )
        }
        TitleText(
            text = value,
            style = TextStyleSize.Large,
            color = valueColor
        )
    }
}