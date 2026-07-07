package com.hye.features.route.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.LabelText
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.theme.AppTheme


@Composable
fun MapSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(AppTheme.dimens.mapPlaceholderHeight)
            .background(
                color = DesignTheme.colors.surfaceVariant,
                shape = RoundedCornerShape(DesignTheme.dimens.radiusMd)
            )
            .border(
                width = DesignTheme.dimens.strokeThin,
                color = DesignTheme.colors.border,
                shape = RoundedCornerShape(DesignTheme.dimens.radiusMd)
            ),
        contentAlignment = Alignment.Center
    ) {
        LabelText(
            text = "경로를 선택해주세요",
            style = TextStyleSize.Medium,
            color = DesignTheme.colors.onSurfaceVariant
        )
    }
}