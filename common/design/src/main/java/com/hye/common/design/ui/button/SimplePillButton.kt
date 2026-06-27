package com.hye.common.design.ui.button


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme


// 알약 모양 컴포넌트 특화 수치
private object PillButtonTokens {
    val ContainerHeight = 80.dp
}

@Composable
fun SimplePillButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    containerHeight: Dp = PillButtonTokens.ContainerHeight,
    containerColor: Color = DesignTheme.colors.background,
    thumbSize: Dp = DesignTheme.dimens.thumbNormal,
    thumbColor: Color = DesignTheme.colors.info,
    thumbIcon: @Composable () -> Unit = { },
    onClick: (() -> Unit)? = null,
    thumbModifier: Modifier = Modifier,
    backgroundContent: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(containerHeight)
            .fillMaxWidth()
            .clip(RoundedCornerShape(containerHeight / 2))
            .background(containerColor)
            .padding(DesignTheme.dimens.spaceXs)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(
                    start = DesignTheme.dimens.spaceXl,
                    end = thumbSize + DesignTheme.dimens.spaceMd
                ),
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }

        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            backgroundContent()
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = thumbModifier
                .align(Alignment.CenterEnd)
                .size(thumbSize)
                .clip(CircleShape)
                .background(thumbColor)
                .then(
                    if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
                )
        ) {
            thumbIcon()
        }
    }
}

@Composable
fun SimplePillButton(
    modifier: Modifier = Modifier,
    text: String,
    subText: String? = null,
    textColor: Color = DesignTheme.colors.onBackground,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    subTextColor: Color = DesignTheme.colors.onSurfaceVariant,
    subTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    onClick: (() -> Unit)? = null,
    containerHeight: Dp = PillButtonTokens.ContainerHeight,
    containerColor: Color = DesignTheme.colors.background,
    thumbSize: Dp = DesignTheme.dimens.thumbNormal,
    thumbColor: Color = DesignTheme.colors.info,
    thumbIcon: @Composable () -> Unit = { }
) {
    SimplePillButton(
        modifier = modifier,
        content = {
            Column(verticalArrangement = Arrangement.Center) {
                if (!subText.isNullOrEmpty()) {
                    Text(subText, style = subTextStyle, color = subTextColor)
                }
                Text(text, style = textStyle, fontWeight = FontWeight.Bold, color = textColor)
            }
        },
        onClick = onClick,
        containerHeight = containerHeight,
        containerColor = containerColor,
        thumbSize = thumbSize,
        thumbColor = thumbColor,
        thumbIcon = thumbIcon
    )
}