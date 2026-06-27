package com.hye.common.design.ui.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme

// 바텀 시트 및 핸들 고유 수치 캡슐화
private object BottomSheetTokens {
    val HandleWidth = 40.dp
    val HandleHeight = 4.dp
    val HandleRadius = 2.dp
}

@Composable
fun CommonBottomSheet(
    modifier: Modifier = Modifier,
    sheetHeightFraction: Float = 0.6f,
    containerColor: Color = DesignTheme.colors.background,
    shape: Shape = RoundedCornerShape(
        topStart = DesignTheme.dimens.radiusXl,
        topEnd = DesignTheme.dimens.radiusXl
    ),
    elevation: Dp = DesignTheme.dimens.elevationMd,

    showDragHandle: Boolean = true,
    onDismissRequest: () -> Unit = {},
    header: @Composable (() -> Unit)? = null,
    bottomArea: @Composable (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    BackHandler(onBack = onDismissRequest)

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        val maxSheetHeight = maxHeight * sheetHeightFraction

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = maxSheetHeight),
            shape = shape,
            color = containerColor,
            shadowElevation = elevation
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (showDragHandle) BottomSheetDragHandle()
                if (header != null) header()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                ) {
                    content()
                }

                if (bottomArea != null) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        bottomArea()
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSheetDragHandle(
    modifier: Modifier = Modifier,
    color: Color = DesignTheme.colors.divider
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = DesignTheme.dimens.spaceMd),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(BottomSheetTokens.HandleWidth)
                .height(BottomSheetTokens.HandleHeight)
                .clip(RoundedCornerShape(BottomSheetTokens.HandleRadius))
                .background(color)
        )
    }
}