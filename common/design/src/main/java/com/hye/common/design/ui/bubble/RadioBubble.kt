package com.hye.common.design.ui.bubble

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.theme.alphaPrimary
import com.hye.common.design.theme.toPx
import com.hye.common.design.theme.toSp
import com.hye.common.design.ui.text.TextBody

// 터미널 HUD 스타일의 이중 테두리 간격 캡슐화
private object RadioBubbleTokens {
    val StrokeGap = 3.dp
}

@Composable
fun RadioBubble(
    message: String,
    color: Color = DesignTheme.colors.info,
    backgroundColor: Color = DesignTheme.colors.background.alphaPrimary,
    fontHeight: TextUnit = DesignTheme.dimens.spaceXl.toSp,
    modifier: Modifier = Modifier,
    isTyping: Boolean = false
) {
    val strokeWidth = DesignTheme.dimens.strokeNormal.toPx
    val gap = RadioBubbleTokens.StrokeGap.toPx

    Box(
        modifier = modifier
            .padding(DesignTheme.dimens.spaceXs)
            .drawBehind {
                fun createHexagonPath(inset: Float): Path {
                    val path = Path()
                    val w = size.width
                    val h = size.height
                    val left = inset
                    val top = inset
                    val right = w - inset
                    val bottom = h - inset
                    val cutSize = h * 0.2f

                    path.moveTo(left, top + cutSize)
                    path.lineTo(left, bottom - cutSize)
                    path.lineTo(left + cutSize * 0.6f, bottom)
                    path.lineTo(right - cutSize * 0.6f, bottom)
                    path.lineTo(right, bottom - cutSize)
                    path.lineTo(right, top + cutSize)
                    path.lineTo(right - cutSize * 0.6f, top)
                    path.lineTo(left + cutSize * 0.6f, top)

                    path.close()
                    return path
                }

                val outerPath = createHexagonPath(0f)
                val innerPath = createHexagonPath(gap + strokeWidth)

                drawPath(path = outerPath, color = backgroundColor, style = Fill)
                drawPath(path = outerPath, color = color, style = Stroke(width = strokeWidth))
                drawPath(
                    path = innerPath,
                    color = color.copy(alpha = 0.6f),
                    style = Stroke(width = strokeWidth)
                )
            }
            .padding(
                horizontal = DesignTheme.dimens.spaceXxl,
                vertical = DesignTheme.dimens.spaceLg
            )
    ) {
        if (isTyping) {
            TypingIndicator(color)
        } else {
            TextBody(
                text = message,
                fontWeight = FontWeight.Bold,
                lineHeight = fontHeight
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF888888)
@Composable
fun PreviewRadioMessage() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioBubble(message = "어쩌고 저쩌고\n재잘재잘")
                Spacer(modifier = Modifier.height(30.dp))
                RadioBubble(message = "사용자의 움직임이 감지되었습니다.\n운동을 시작할까요?")
                Spacer(modifier = Modifier.height(30.dp))
                RadioBubble(message = "", isTyping = true)
            }
        }
    }
}