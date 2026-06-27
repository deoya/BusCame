package com.hye.common.design.ui.card

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme

private object ModernSelectionTokens {
    val Height = 64.dp

    private object PageIndicatorTokens {
        val DotSize = 8.dp
        val DotWidthSelected = 24.dp
    }

    @Composable
    fun ModernSelectionCard(text: String, isSelected: Boolean, onClick: () -> Unit) {
        val borderColor =
            if (isSelected) DesignTheme.colors.info else DesignTheme.colors.disabledContainer
        val backgroundColor =
            if (isSelected) DesignTheme.colors.infoContainer else DesignTheme.colors.background

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(ModernSelectionTokens.Height)
                .clickable { onClick() },
            shape = RoundedCornerShape(DesignTheme.dimens.radiusMd),
            color = backgroundColor,
            border = BorderStroke(DesignTheme.dimens.strokeNormal, borderColor)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = DesignTheme.dimens.spaceLg),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) DesignTheme.colors.info else DesignTheme.colors.onBackground
                )

                if (isSelected) {
                    Icon(Icons.Outlined.CheckCircle, null, tint = DesignTheme.colors.info)
                } else {
                    Icon(
                        Icons.Outlined.RadioButtonUnchecked,
                        null,
                        tint = DesignTheme.colors.infoContainer
                    )
                }
            }
        }
    }

    @Composable
    fun PageIndicator(totalPages: Int, currentPage: Int) {
        Row(horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXs)) {
            repeat(totalPages) { page ->
                val isSelected = page == currentPage
                val width by animateDpAsState(
                    if (isSelected) PageIndicatorTokens.DotWidthSelected else PageIndicatorTokens.DotSize,
                    label = "dot"
                )
                val color =
                    if (isSelected) DesignTheme.colors.info else DesignTheme.colors.infoContainer

                Box(
                    modifier = Modifier
                        .height(PageIndicatorTokens.DotSize)
                        .width(width)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}