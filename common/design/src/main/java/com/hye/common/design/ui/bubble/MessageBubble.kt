package com.hye.common.design.ui.bubble

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.theme.toSp
import com.hye.common.design.ui.text.TypewriterText

@Composable
fun MessageBubble(
    text: String,
    color: Color = Color.White,
    content: (@Composable (String) -> Unit)? = null
) {
    Surface(
        color = Color.Black.copy(alpha = 0.3f),
        shape = RoundedCornerShape(DesignTheme.dimens.radiusMd),
        border = BorderStroke(DesignTheme.dimens.strokeThin, Color.White.copy(alpha = 0.2f)),
        modifier = Modifier.padding(horizontal = DesignTheme.dimens.spaceXxl)
    ) {
        if (content != null) {
            content(text)
        } else {
            TypewriterText(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    lineHeight = DesignTheme.dimens.spaceXl.toSp,
                    textAlign = TextAlign.Center
                ),
                color = color,
                modifier = Modifier.padding(DesignTheme.dimens.spaceLg)
            )
        }
    }
}
