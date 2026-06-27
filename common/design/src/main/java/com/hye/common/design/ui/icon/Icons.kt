package com.hye.common.design.ui.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.SwipeLeftAlt
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.common.light

private object ActionIconTokens {
    val SlideIconSize = 28.dp
}

@Composable
fun CloseIcon(
    image: ImageVector = Icons.Outlined.Close,
    color: Color = DesignTheme.colors.info,
    size: Dp = DesignTheme.dimens.iconSmall
) = Icon(
    imageVector = image,
    contentDescription = "Close",
    modifier = Modifier.size(size),
    tint = color
)

@Composable
fun CalendarIcon(
    image: ImageVector = Icons.Default.CalendarToday,
    color: Color = DesignTheme.colors.onSurfaceVariant.light,
    size: Dp = DesignTheme.dimens.iconSmall
) = Icon(
    imageVector = image,
    contentDescription = "Calendar",
    tint = color,
    modifier = Modifier.size(size)
)

@Composable
fun ArrowLeftIcon(
    image: ImageVector = Icons.Filled.KeyboardDoubleArrowLeft,
    color: Color = DesignTheme.colors.info.light,
    size: Dp = DesignTheme.dimens.iconLarge
) = Icon(
    imageVector = image,
    contentDescription = "ArrowLeft Hint Icon",
    tint = color,
    modifier = Modifier.size(size)
)

@Composable
fun SlideIcon(
    image: ImageVector = Icons.Filled.SwipeLeftAlt,
    color: Color = DesignTheme.colors.background,
    size: Dp = ActionIconTokens.SlideIconSize,
) = Icon(
    imageVector = image,
    contentDescription = "Slide Action",
    tint = color,
    modifier = Modifier.size(size)
)

@Composable
fun SimplePillIcon(
    image: ImageVector = Icons.Filled.TouchApp,
    color: Color = DesignTheme.colors.background,
    size: Dp = ActionIconTokens.SlideIconSize,
) = Icon(
    imageVector = image,
    contentDescription = "Touch Action",
    tint = color,
    modifier = Modifier.size(size)
)