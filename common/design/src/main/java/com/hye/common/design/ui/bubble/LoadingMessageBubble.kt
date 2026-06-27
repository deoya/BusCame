package com.hye.common.design.ui.bubble

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.theme.toSp

private object TypingIndicatorTokens {
    val ContainerWidth = 40.dp
    val DotSize = 6.dp
    val DotPadding = 2.dp
}

@Composable
fun LoadingMessageBubble(text: String, color: Color = Color.White) {
    val inlineId = "typingIndicator"
    val annotatedText = buildAnnotatedString {
        append(text)
        appendInlineContent(inlineId, "[...]")
    }
    val inlineContent = mapOf(
        inlineId to InlineTextContent(
            Placeholder(
                width = 3.em,
                height = 1.em,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TypingIndicator(color = color)
            }
        }
    )

    Text(
        text = annotatedText,
        inlineContent = inlineContent,
        modifier = Modifier.padding(DesignTheme.dimens.spaceLg),
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
            lineHeight = DesignTheme.dimens.spaceXl.toSp,
            textAlign = TextAlign.Center
        ),
        color = color
    )
}

@Composable
fun TypingIndicator(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "typing")
    val dots = List(3) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 1200
                    0.0f at 0 with FastOutSlowInEasing
                    1.0f at 300 with FastOutSlowInEasing
                    0.0f at 600 with FastOutSlowInEasing
                    0.0f at 1200
                },
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset(index * 200)
            ),
            label = "dotAlpha_$index"
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.width(TypingIndicatorTokens.ContainerWidth)
    ) {
        dots.forEach { alphaState ->
            Box(
                modifier = Modifier
                    .padding(horizontal = TypingIndicatorTokens.DotPadding)
                    .size(TypingIndicatorTokens.DotSize)
                    .alpha(alphaState.value)
                    .background(color, CircleShape)
            )
        }
    }
}