package com.hye.common.design.ui.bubble

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.common.light
import com.hye.common.design.ui.text.LabelText
import com.hye.common.design.ui.text.TextStyleSize

@Composable
fun FeedbackBubble(message: String) {
    Surface(
        color = DesignTheme.colors.primary.light,
        shape = RoundedCornerShape(DesignTheme.dimens.radiusMd)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = DesignTheme.dimens.spaceMd,
                vertical = DesignTheme.dimens.spaceSm
            ),
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXs)
        ) {
            Icon(
                Icons.Default.AutoAwesome,
                null,
                tint = DesignTheme.colors.background,
                modifier = Modifier.size(DesignTheme.dimens.iconMedium)
            )
            LabelText(
                message,
                color = DesignTheme.colors.background,
                fontWeight = FontWeight.Bold,
                style = TextStyleSize.Small
            )
        }
    }
}