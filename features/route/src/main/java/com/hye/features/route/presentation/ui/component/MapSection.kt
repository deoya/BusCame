package com.hye.features.route.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.theme.AppTheme
import com.hye.domain.model.route.SelectionMode
import com.kakao.vectormap.KakaoMap
import timber.log.Timber

@Composable
fun MapSection(
    selectionMode: SelectionMode,
    onMapCenterChanged: (Double, Double) -> Unit,
    onConfirmSelection: () -> Unit
) {
    var kakaoMapInstance by remember { mutableStateOf<KakaoMap?>(null) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            //.height(AppTheme.dimens.mapPlaceholderHeight)
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
        Timber.d("카카오 맵 시작!")
        AppKakaoMap(
            onMapReady = { map ->
                kakaoMapInstance = map

                map.setOnCameraMoveEndListener { _, cameraPosition, gestureType ->
                    if (gestureType != null) { // 사용자가 손으로 움직였을 때만!
                        onMapCenterChanged(
                            cameraPosition.position.latitude,
                            cameraPosition.position.longitude
                        )
                    }
                }
            }
        )
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "선택 핀",
            tint = AppTheme.color.markerFinColor,
            modifier = Modifier
                .size(48.dp)
                .offset(y = (-24).dp)
        )


        AnimatedVisibility(
            visible = selectionMode != SelectionMode.NONE,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            val buttonText =
                if (selectionMode == SelectionMode.DEPARTURE) "여기를 출발지로 설정" else "여기를 도착지로 설정"

            Button(
                onClick = onConfirmSelection,
                colors = ButtonDefaults.buttonColors(containerColor = DesignTheme.colors.primary)
            ) {
                Text(text = buttonText, color = DesignTheme.colors.onPrimary)
            }
        }

    }
}