package com.hye.common.design.ui.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.LabelMedium
import com.hye.common.design.util.clickableSingle


@Composable
fun Tag(name: String, color: Color = DesignTheme.colors.info) =
    LabelMedium(text = name, color = color, fontWeight = FontWeight.Bold)


@Composable
fun StyledTag(
    onClick: (String) -> Unit = {},
    color: Color = DesignTheme.colors.infoContainer,
    border: BorderStroke? = null,
    shape: Dp = DesignTheme.dimens.radiusSm,

    horizontal: Dp = DesignTheme.dimens.spaceSm,
    vertical: Dp = DesignTheme.dimens.spaceXxs,

    content: @Composable () -> Unit = {},
    content2: @Composable () -> Unit = {},
) {
    Surface(
        shape = RoundedCornerShape(shape),
        color = color,
        border = border,
        modifier = Modifier.clickableSingle(onClick = { onClick })
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = horizontal,
                vertical = vertical
            ),
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXxs)
        ) {
            content()
            content2()
        }
    }
}
