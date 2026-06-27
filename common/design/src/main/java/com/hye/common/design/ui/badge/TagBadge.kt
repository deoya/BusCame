package com.hye.common.design.ui.badge


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.LabelText
import com.hye.common.design.ui.text.TextStyleSize

@Composable
fun TagBadge(
    text: String,
    bgColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = DesignTheme.dimens.strokeThin,
    shape: Shape = RoundedCornerShape(DesignTheme.dimens.radiusXl),
    contentPadding: PaddingValues = PaddingValues(
        horizontal = DesignTheme.dimens.spaceSm,
        vertical = DesignTheme.dimens.spaceXxs
    ),
    textStyle: TextStyleSize = TextStyleSize.Medium,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Box(
        modifier = modifier
            .background(color = bgColor, shape = shape)
            .then(
                if (borderColor != Color.Transparent && borderWidth > 0.dp) {
                    Modifier.border(width = borderWidth, color = borderColor, shape = shape)
                } else Modifier
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        LabelText(
            text = text,
            style = textStyle,
            fontWeight = fontWeight,
            color = textColor,
            modifier = Modifier.padding(bottom = 0.dp)
        )
    }
}