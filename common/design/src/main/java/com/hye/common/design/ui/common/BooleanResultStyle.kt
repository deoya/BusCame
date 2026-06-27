package com.hye.common.design.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme

// 🟢 1. 아이콘 (Icons)
@ReadOnlyComposable
@Composable
fun Boolean.completedIcon(
    completed: ImageVector = Icons.Default.Check,
    incomplete: ImageVector = Icons.Default.Work
): ImageVector = if (this) completed else incomplete

val Boolean.playPauseIcon: ImageVector
    @ReadOnlyComposable
    @Composable
    get() = if (this) Icons.Default.Pause else Icons.Default.PlayArrow

// 🟢 2. 테두리 (Borders)
@ReadOnlyComposable
@Composable
fun Boolean.selectionBorderStroke(
    width: Dp = DesignTheme.dimens.strokeThin, // 기존 one
    color: Color = DesignTheme.colors.disabled
): BorderStroke? = if (this) null else BorderStroke(width, color)

@ReadOnlyComposable
@Composable
fun Boolean.completedBorderWidth(
    completed: Dp = DesignTheme.dimens.elevationNone,
    incomplete: Dp = DesignTheme.dimens.strokeThin
): Dp = if (this) completed else incomplete

@ReadOnlyComposable
@Composable
fun Boolean.completedCardBorder(
    completed: BorderStroke = BorderStroke(
        DesignTheme.dimens.elevationNone,
        Color.Transparent
    ),
    incomplete: BorderStroke = BorderStroke(
        0.dp,
        DesignTheme.colors.disabledContainer
    )
): BorderStroke = if (this) completed else incomplete

// 🟢 3. 텍스트 (Typography)
val Boolean.selectionFontWeight: FontWeight
    @ReadOnlyComposable
    @Composable
    get() = if (this) FontWeight.Bold else FontWeight.Medium

// 🟢 4. 색상 (Colors)
@ReadOnlyComposable
@Composable
fun Boolean.selectionBtnColor(
    selection: Color = DesignTheme.colors.info,
    deselection: Color = DesignTheme.colors.background
): Color = if (this) selection else deselection

@ReadOnlyComposable
@Composable
fun Boolean.selectionContentColor(
    selection: Color = DesignTheme.colors.background,
    deselection: Color = DesignTheme.colors.onSurfaceVariant
): Color = if (this) selection else deselection

val Boolean.selectionBtnColorLight: Color
    @ReadOnlyComposable
    @Composable
    get() = if (this) DesignTheme.colors.infoContainer else DesignTheme.colors.surfaceVariant

@ReadOnlyComposable
@Composable
fun Boolean.completedColor(
    completed: Color = DesignTheme.colors.disabled,
    incomplete: Color = DesignTheme.colors.disabledContainer
): Color = if (this) completed else incomplete

@ReadOnlyComposable
@Composable
fun Boolean.enabledColor(
    enabled: Color = DesignTheme.colors.info,
    disabled: Color = DesignTheme.colors.disabled
): Color = if (this) enabled else disabled

val Boolean.selectionBorderColor: Color
    @ReadOnlyComposable
    @Composable
    get() = if (this) DesignTheme.colors.info else DesignTheme.colors.disabled

// 🟢 5. 그림자 및 높이 (Elevation)
@ReadOnlyComposable
@Composable
fun Boolean.completedElevation(
    completed: Dp = DesignTheme.dimens.elevationNone,
    incomplete: Dp = DesignTheme.dimens.elevationXs
): Dp = if (this) completed else incomplete
