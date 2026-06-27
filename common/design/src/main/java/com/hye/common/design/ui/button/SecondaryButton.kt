package com.hye.common.design.ui.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme

// 기본 버튼 특화 수치
private object DefaultButtonTokens {
    val PrimaryElevation = 2.dp
}

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = DesignTheme.colors.info,
    contentColor: Color = DesignTheme.colors.background
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(DesignTheme.dimens.radiusMd),
        modifier = modifier.height(DesignTheme.dimens.buttonHeight),
        elevation = ButtonDefaults.buttonElevation(DefaultButtonTokens.PrimaryElevation)
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = DesignTheme.colors.badgeContainer,
    contentColor: Color = DesignTheme.colors.onBadge
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(DesignTheme.dimens.radiusMd),
        modifier = modifier.height(DesignTheme.dimens.buttonHeight),
        elevation = ButtonDefaults.buttonElevation(DesignTheme.dimens.elevationNone)
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}