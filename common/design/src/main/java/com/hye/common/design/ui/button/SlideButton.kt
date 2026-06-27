package com.hye.common.design.ui.button

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.icon.ArrowLeftIcon
import com.hye.common.design.ui.icon.SlideIcon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private object SlideButtonTokens {
    val ContainerHeight = 80.dp
}

@Composable
fun SlideButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onSlideComplete: () -> Unit = {},
    containerHeight: Dp = SlideButtonTokens.ContainerHeight,
    containerColor: Color = DesignTheme.colors.background,
    thumbSize: Dp = DesignTheme.dimens.thumbNormal,
    thumbColor: Color = DesignTheme.colors.info,
    thumbIcon: @Composable () -> Unit = { SlideIcon() },
    arrowHintColor: Color = thumbColor
) {
    val density = LocalDensity.current
    val thumbSizePx = with(density) { thumbSize.toPx() }
    val scope = rememberCoroutineScope()

    val offsetX = remember { Animatable(0f) }
    var trackWidthPx by remember { mutableFloatStateOf(0f) }

    val draggableState = rememberDraggableState { delta ->
        val maxDragDistance = trackWidthPx - thumbSizePx
        val newOffset = offsetX.value + delta
        scope.launch {
            offsetX.snapTo(newOffset.coerceIn(-maxDragDistance, 0f))
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        LaunchedEffect(maxWidth) {
            trackWidthPx = with(density) { maxWidth.toPx() }
        }

        SimplePillButton(
            content = content,
            containerHeight = containerHeight,
            containerColor = containerColor,
            thumbSize = thumbSize,
            thumbColor = thumbColor,
            thumbIcon = thumbIcon,
            onClick = null,
            thumbModifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        val maxDragDistance = trackWidthPx - thumbSizePx
                        val threshold = -(maxDragDistance * 0.7f)

                        if (offsetX.value <= threshold) {
                            onSlideComplete()
                            scope.launch {
                                delay(300)
                                offsetX.animateTo(0f, animationSpec = tween(500))
                            }
                        } else {
                            scope.launch {
                                offsetX.animateTo(0f, animationSpec = tween(500))
                            }
                        }
                    }
                ),
            backgroundContent = {
                Row(
                    modifier = Modifier
                        .padding(end = thumbSize + DesignTheme.dimens.spaceXs)
                        .alpha(DesignTheme.dimens.alphaMuted),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ArrowLeftIcon(color = arrowHintColor)
                }
            }
        )
    }
}

@Preview
@Composable
fun SlideButton_pre() {
    SlideButton(text = "1 / 15")
}

@Composable
fun SlideButton(
    modifier: Modifier = Modifier,
    text: String,
    subText: String? = null,
    textColor: Color = DesignTheme.colors.onBackground,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    subTextColor: Color = DesignTheme.colors.onSurfaceVariant,
    subTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    onSlideComplete: () -> Unit = {},
    containerHeight: Dp = SlideButtonTokens.ContainerHeight,
    containerColor: Color = DesignTheme.colors.background,
    thumbSize: Dp = DesignTheme.dimens.thumbNormal,
    thumbColor: Color = DesignTheme.colors.info,
    thumbIcon: @Composable () -> Unit = { SlideIcon() },
    arrowHintColor: Color = thumbColor
) {
    SlideButton(
        modifier = modifier,
        onSlideComplete = onSlideComplete,
        containerHeight = containerHeight,
        containerColor = containerColor,
        thumbSize = thumbSize,
        thumbColor = thumbColor,
        thumbIcon = thumbIcon,
        arrowHintColor = arrowHintColor,
        content = {
            Column(verticalArrangement = Arrangement.Center) {
                if (!subText.isNullOrEmpty()) {
                    Text(subText, style = subTextStyle, color = subTextColor)
                }
                Text(text, style = textStyle, fontWeight = FontWeight.Bold, color = textColor)
            }
        }
    )
}