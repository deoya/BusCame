package com.hye.common.design.ui.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.util.ClickGuard.RememberSafeClick

object StyledButton {
    @Composable
    operator fun invoke(
        onClick: () -> Unit,
        shape: Dp = DesignTheme.dimens.radiusMd,
        containerColor: Color = DesignTheme.colors.info,
        contentColor: Color = DesignTheme.colors.background,
        elevation: Dp = DesignTheme.dimens.elevationNone,
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(shape),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            elevation = ButtonDefaults.buttonElevation(elevation),
            modifier = modifier,
            contentPadding = contentPadding
        ) {
            content()
        }
    }

    @Composable
    fun SafeClick(
        onClick: () -> Unit,
        shape: Dp = DesignTheme.dimens.radiusMd,
        containerColor: Color = DesignTheme.colors.info,
        contentColor: Color = DesignTheme.colors.background,
        elevation: Dp = DesignTheme.dimens.elevationNone,
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        invoke(
            onClick = RememberSafeClick { onClick },
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = elevation,
            contentPadding = contentPadding,
            modifier = modifier,
            content = content
        )
    }
}

object StyledIconButton {
    @Composable
    operator fun invoke(
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            icon()
        }
    }

    @Composable
    fun SafeClick(
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        invoke(
            onClick = RememberSafeClick { onClick },
            icon = icon,
            modifier = modifier
        )
    }
}