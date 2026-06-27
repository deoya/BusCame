package com.hye.common.design.ui.progress

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.util.Calculator

// 프로그레스 바 고유 수치 캡슐화
private object CircularProgressTokens {
    val DefaultSize = 88.dp
    val StrokeWidth = 8.dp
    val DurationMillis = 1000
}

@Preview(backgroundColor = 0xFFBDBDBD, showBackground = true)
@Composable
fun CircularProgressBar_Preview() {
    val progress = Calculator.progress(10, 5)
    CircularProgressBar(
        progress = progress,
        iconContent = {
            Text(progress.toString())
        }
    )
}

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float, // 0.0f ~ 1.0f
    iconContent: @Composable () -> Unit = {},
    size: Dp = CircularProgressTokens.DefaultSize,
    color: Color = DesignTheme.colors.background,
    strokeWidth: Dp = CircularProgressTokens.StrokeWidth,
    backgroundAlpha: Float = DesignTheme.dimens.alphaMuted
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = CircularProgressTokens.DurationMillis,
            easing = LinearOutSlowInEasing
        ),
        label = "CircularProgressAnimation"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        CircularProgressIndicator(
            progress = { 1f },
            modifier = Modifier.fillMaxSize(),
            color = color.copy(alpha = backgroundAlpha),
            trackColor = Color.Transparent,
            strokeWidth = strokeWidth,
        )

        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.fillMaxSize(),
            color = color,
            strokeWidth = strokeWidth,
            trackColor = Color.Transparent,
            strokeCap = StrokeCap.Round
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(size - strokeWidth * 2)
        ) {
            iconContent()
        }
    }
}