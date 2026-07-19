package com.hye.features.route.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hye.common.design.theme.DesignTheme
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode
import com.hye.features.route.R
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTextBuilder
import com.kakao.vectormap.label.LabelTextStyle
import timber.log.Timber

@Composable
fun MapSection(
    selectionMode: SelectionMode,
    currentMapCenter: Pair<Double, Double>? = null,
    nearbyStops: List<BusStop>,
    selectedBusStop: BusStop?,
    onMapCenterChanged: (Double, Double) -> Unit,
    onBusStopPinClick: (BusStop) -> Unit,
    onConfirmSelection: () -> Unit
) {
    var kakaoMapInstance by remember { mutableStateOf<KakaoMap?>(null) }
    // 마커들의 ID를 기억하는 Set
    val drawnMarkerIds = remember { mutableSetOf<String>() }

    var mapReadyTrigger by remember { mutableStateOf(0) }

    // 1. 카메라 강제 이동 Effect
    LaunchedEffect(kakaoMapInstance, currentMapCenter) {
        val map = kakaoMapInstance ?: return@LaunchedEffect
        val center = currentMapCenter ?: return@LaunchedEffect

        val targetLatLng = LatLng.from(center.first, center.second)
        val currentLatLng = map.cameraPosition?.position

        if (currentLatLng != null) {
            val latDiff = kotlin.math.abs(currentLatLng.latitude - targetLatLng.latitude)
            val lngDiff = kotlin.math.abs(currentLatLng.longitude - targetLatLng.longitude)
            if (latDiff < 0.0001 && lngDiff < 0.0001) {
                return@LaunchedEffect
            }
        }
        map.moveCamera(CameraUpdateFactory.newCenterPosition(targetLatLng, 16))
    }
    // 2. 스마트 렌더링 및 스타일 생성 Effect
    LaunchedEffect(kakaoMapInstance, nearbyStops, selectedBusStop) {
        val map = kakaoMapInstance ?: return@LaunchedEffect
        val manager = map.labelManager ?: return@LaunchedEffect
        val layer = manager.layer ?: return@LaunchedEffect

        // API로 불러온 주변 정류장 리스트와 선택된 정거장 정보 합치기
        val combinedStops = (nearbyStops + listOfNotNull(selectedBusStop)).distinctBy { it.nodeId }

        if (nearbyStops.isEmpty()) {
            layer.removeAll()
            drawnMarkerIds.clear()
            return@LaunchedEffect
        }

        // 2-A. 기본 스타일 등록 (글씨 없음)
        var unselectedStyle = manager.getLabelStyles("busStopPinStyle")
        if (unselectedStyle == null) {
            unselectedStyle = manager.addLabelStyles(
                LabelStyles.from(
                    "busStopPinStyle",
                    LabelStyle.from(R.drawable.ic_bus_stop)
                )
            )
        }

        // 2-B. 선택 스타일 등록 (두꺼운 외곽선 글씨체 포함)
        var selectedStyle = manager.getLabelStyles("busStopSelectedStyle")
        if (selectedStyle == null) {
            val textStyle = LabelTextStyle.from(
                42,
                android.graphics.Color.BLACK,
                5,
                android.graphics.Color.WHITE
            )
            selectedStyle = manager.addLabelStyles(
                LabelStyles.from(
                    "busStopSelectedStyle",
                    LabelStyle.from(R.drawable.ic_bus_stop_selected)
                        .setTextStyles(textStyle)
                )
            )
        }

        val newIds = combinedStops.map { it.nodeId }.toSet()

        // 생명주기 등으로 리셋되었을 때 안전하게 동기화 복구
        if (mapReadyTrigger > 1) {
            drawnMarkerIds.clear()
        }
        // 사라진 핀 제거
        val idsToRemove = drawnMarkerIds - newIds
        idsToRemove.forEach { id -> layer.getLabel(id)?.let { layer.remove(it) } }

        // 새로운 핀 추가
        val stopsToAdd = combinedStops.filter { it.nodeId !in drawnMarkerIds }
        stopsToAdd.forEach { stop ->
            // nodeId가 없거나 좌표가 이상하면 아예 무시
            if (stop.nodeId.isBlank() || stop.latitude == 0.0 || stop.longitude == 0.0) return@forEach
            runCatching {
                val options =
                    LabelOptions.from(stop.nodeId, LatLng.from(stop.latitude, stop.longitude))
                        .setStyles(if (selectedBusStop?.nodeId == stop.nodeId) selectedStyle else unselectedStyle)
                        .setTexts(LabelTextBuilder().setTexts(stop.name))
                        .setClickable(true)
                layer.addLabel(options)
            }.onFailure {
                Timber.e(it, "📍 핀 추가 실패: ${stop.name}")
            }
        }
        drawnMarkerIds.clear()
        drawnMarkerIds.addAll(newIds)
    }
    // 3. 선택 변경에 따른 실시간 스타일 스위칭 Effect
    LaunchedEffect(selectedBusStop) {
        val map = kakaoMapInstance ?: return@LaunchedEffect
        val layer = map.labelManager?.layer ?: return@LaunchedEffect
        val manager = map.labelManager ?: return@LaunchedEffect

        drawnMarkerIds.forEach { id ->
            val label = layer.getLabel(id) ?: return@forEach
            if (id == selectedBusStop?.nodeId) {
                label.changeStyles(manager.getLabelStyles("busStopSelectedStyle"))
                if (label.texts.isNotEmpty()) {
                    label.changeText(LabelTextBuilder().setTexts(label.texts[0]))
                }
            } else {
                label.changeStyles(manager.getLabelStyles("busStopPinStyle"))
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
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
        AppKakaoMap(
            onMapReady = { map ->
                kakaoMapInstance = map
                mapReadyTrigger++
                map.setOnCameraMoveEndListener { _, cameraPosition, gestureType ->
                    if (gestureType != null) {
                        onMapCenterChanged(
                            cameraPosition.position.latitude,
                            cameraPosition.position.longitude
                        )
                    }
                }
            },
            currentMapCenter = currentMapCenter,
            onLabelClick = { clickedId ->
                val clickedStop = nearbyStops.find { it.nodeId == clickedId }
                if (clickedStop != null) {
                    onBusStopPinClick(clickedStop)
                } else {
                    Timber.e("💥 [MapSection] 매핑 실패! 리스트에 없는 ID입니다: $clickedId")
                }
            }
        )
        // 하단 동작 설정 버튼 UI
        AnimatedVisibility(
            visible = selectionMode != SelectionMode.NONE,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = DesignTheme.dimens.spaceMd)
        ) {
            val isPinSelected = selectedBusStop != null
            val buttonText = if (isPinSelected) {
                "'${selectedBusStop?.name}' 선택하기"
            } else {
                "지도에서 정류장 핀을 선택해주세요"
            }
            Button(
                onClick = onConfirmSelection,
                enabled = isPinSelected,
                colors = ButtonDefaults.buttonColors(
                    containerColor = DesignTheme.colors.primary,
                    disabledContainerColor = DesignTheme.colors.disabled
                )
            ) {
                Text(
                    text = buttonText,
                    color = if (isPinSelected) DesignTheme.colors.onPrimary else DesignTheme.colors.onSurfaceVariant
                )
            }
        }

    }
}