package com.hye.features.arrival.presentation.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.button.StyledButton
import com.hye.common.design.ui.text.BodyText
import com.hye.common.design.ui.text.TextStyleSize

@Composable
fun BoardToggleButton(isBoarded: Boolean, onClick: () -> Unit) {
    StyledButton(
        onClick = onClick,
        containerColor = if (isBoarded) DesignTheme.colors.disabled else DesignTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                DesignTheme.dimens.strokeThin,
                DesignTheme.colors.border,
                RoundedCornerShape(DesignTheme.dimens.radiusMd)
            ),
    ) {
        BodyText(
            text = if (isBoarded) "탑승 취소" else "탑승 완료",
            style = TextStyleSize.Medium,
            fontWeight = FontWeight.Medium,
            color = DesignTheme.colors.onBackground
        )
    }
}