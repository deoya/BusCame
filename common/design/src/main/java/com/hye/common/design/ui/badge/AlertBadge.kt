package com.hye.common.design.ui.badge

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlertBadge(isBadgeVisible: Boolean, content: @Composable () -> Unit) {
    BadgedBox(
        badge = {
            if (isBadgeVisible) {
                Badge(modifier = Modifier.size(8.dp), containerColor = Color.Red)
            }
        }
    ) {
        content()
    }

}