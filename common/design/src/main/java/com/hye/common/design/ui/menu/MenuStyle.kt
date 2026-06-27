package com.hye.common.design.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.common.IconStyle
import com.hye.common.design.ui.common.Orientation

private object MenuTokens {
    val DefaultIconSize = 28.dp
}

data class MenuStyle(
    val modifier: Modifier? = null,
    val icon: IconStyle? = null,
    val spacedBy: Dp? = null
)

@Composable
fun StyledMenu(
    style: MenuStyle,
    content: @Composable () -> Unit,
    orientation: Orientation = Orientation.Column
) {
    val icon: @Composable () -> Unit = {
        if (style.icon?.image != null)
            Icon(
                imageVector = style.icon.image,
                contentDescription = style.icon.contentDescription,
                tint = style.icon.tint ?: DesignTheme.colors.infoContainer,
                modifier = Modifier.size(style.icon.size ?: MenuTokens.DefaultIconSize)
            )
    }

    // 반복되는 기본 여백 Modifier 통합
    val defaultPadding = Modifier.padding(
        vertical = DesignTheme.dimens.spaceLg,
        horizontal = DesignTheme.dimens.spaceXxs
    )

    when (orientation) {
        Orientation.Column -> {
            Column(
                modifier = style.modifier ?: defaultPadding.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    style.spacedBy ?: DesignTheme.dimens.spaceXs
                )
            ) {
                icon()
                content()
            }
        }

        Orientation.Row -> {
            Row(
                modifier = style.modifier ?: defaultPadding.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    style.spacedBy ?: DesignTheme.dimens.spaceXs
                )
            ) {
                icon()
                content()
            }
        }
    }
}