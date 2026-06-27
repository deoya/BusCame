package com.hye.common.design.ui.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.hye.common.design.theme.DesignTheme

enum class TextStyleSize {
    Small,
    Medium,
    Large,
}

// 🟢 1. Display & Headline 그룹 (아주 큰 화면 제목용)
@Composable
fun DisplayText(
    text: String,
    color: Color = DesignTheme.colors.onSurfaceVariant,
    weight: FontWeight = FontWeight.Bold,
    size: TextUnit = TextUnit.Unspecified, // 폰트 크기를 외부에서 강제해야 할 때만 사용
    letterSpacing: TextUnit = TextUnit.Unspecified,
    modifier: Modifier = Modifier
) = Text(
    text = text,
    style = MaterialTheme.typography.displaySmall,
    fontSize = size,
    fontWeight = weight,
    color = color,
    letterSpacing = letterSpacing,
    modifier = modifier
)

@Composable
fun SectionText(
    text: String,
    style: TextStyleSize = TextStyleSize.Medium,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = DesignTheme.colors.onBackground,
    modifier: Modifier = Modifier
) {
    val resultStyle = when (style) {
        TextStyleSize.Small -> MaterialTheme.typography.headlineSmall
        TextStyleSize.Medium -> MaterialTheme.typography.headlineMedium
        TextStyleSize.Large -> MaterialTheme.typography.headlineLarge
    }
    Text(
        text = text,
        style = resultStyle,
        fontWeight = fontWeight,
        color = color,
        modifier = modifier
    )
}

// 🟢 2. Title 그룹 (각 영역의 소제목용)
@Composable
fun TitleText(
    text: String,
    style: TextStyleSize = TextStyleSize.Medium,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = DesignTheme.colors.onBackground,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    modifier: Modifier = Modifier
) {
    val resultStyle = when (style) {
        TextStyleSize.Small -> MaterialTheme.typography.titleSmall
        TextStyleSize.Medium -> MaterialTheme.typography.titleMedium
        TextStyleSize.Large -> MaterialTheme.typography.titleLarge
    }
    Text(
        text = text,
        style = resultStyle,
        fontWeight = fontWeight,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier
    )
}

// 🟢 3. Body 그룹 (일반적인 본문/설명글용)
@Composable
fun BodyText(
    text: String,
    style: TextStyleSize = TextStyleSize.Medium,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = DesignTheme.colors.onBackground,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    modifier: Modifier = Modifier
) {
    val resultStyle = when (style) {
        TextStyleSize.Small -> MaterialTheme.typography.bodySmall
        TextStyleSize.Medium -> MaterialTheme.typography.bodyMedium
        TextStyleSize.Large -> MaterialTheme.typography.bodyLarge
    }
    Text(
        text = text,
        style = resultStyle,
        fontWeight = fontWeight,
        color = color,
        lineHeight = lineHeight,
        maxLines = maxLines ?: Int.MAX_VALUE,
        overflow = overflow,
        modifier = modifier
    )
}

// 🟢 4. Label 그룹 (버튼 내부, 태그, 에러 메시지 등 작은 UI 요소용)
@Composable
fun LabelText(
    text: String,
    style: TextStyleSize = TextStyleSize.Medium,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = DesignTheme.colors.onSurfaceVariant,
    lineHeight: TextUnit = TextUnit.Unspecified,
    modifier: Modifier = Modifier.padding(bottom = DesignTheme.dimens.spaceXxs) // 기존 xxxxs
) {
    val resultStyle = when (style) {
        TextStyleSize.Small -> MaterialTheme.typography.labelSmall
        TextStyleSize.Medium -> MaterialTheme.typography.labelMedium
        TextStyleSize.Large -> MaterialTheme.typography.labelLarge
    }
    Text(
        text = text,
        style = resultStyle,
        fontWeight = fontWeight,
        color = color,
        lineHeight = lineHeight,
        modifier = modifier
    )
}

// 🟢 5. 특수 목적 텍스트 컴포넌트
@Composable
fun ErrorMessage(
    errorMessage: String,
    errorColor: Color = DesignTheme.colors.error,
    modifier: Modifier = Modifier.padding(
        top = DesignTheme.dimens.spaceXxs, // 기존 xxxxs
        start = DesignTheme.dimens.spaceXs // 기존 xxs
    )
) = Text(
    text = errorMessage,
    color = errorColor,
    style = MaterialTheme.typography.labelSmall,
    modifier = modifier
)