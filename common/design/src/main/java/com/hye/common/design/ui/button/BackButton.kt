package com.hye.common.design.ui.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme


private object BackButtonTokens {
    val TopMargin = 48.dp
}

@Composable
fun BackButton(
    onBack: () -> Unit,
    applyShadow: Boolean = true,
    color: Color = DesignTheme.colors.background,
    modifier: Modifier = Modifier.padding(
        top = BackButtonTokens.TopMargin,
        start = DesignTheme.dimens.spaceLg
    )
) {
    Column(modifier = modifier) {
        Surface(
            color = color,
            shape = CircleShape,
            shadowElevation = if (applyShadow) DesignTheme.dimens.elevationSm else DesignTheme.dimens.elevationNone,
            modifier = Modifier.clickable { onBack() }
        ) {
            Icon(
                Icons.Default.ArrowBack,
                null,
                modifier = Modifier.padding(DesignTheme.dimens.spaceXs)
            )
        }
    }
}