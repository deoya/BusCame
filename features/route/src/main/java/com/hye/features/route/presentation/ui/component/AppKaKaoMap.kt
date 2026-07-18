package com.hye.features.route.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.kakao.vectormap.GestureType
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import timber.log.Timber

@Composable
fun AppKakaoMap(
    modifier: Modifier = Modifier,
    onMapReady: (KakaoMap) -> Unit,
    onLabelClick: (String) -> Unit = {},
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var mapViewRef by remember { mutableStateOf<MapView?>(null) }
    val currentOnLabelClick by rememberUpdatedState(onLabelClick)

    DisposableEffect(lifecycleOwner, mapViewRef) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapViewRef?.resume()
                Lifecycle.Event.ON_PAUSE -> mapViewRef?.pause()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            val mapView = MapView(context)
            mapViewRef = mapView

            mapView.start(
                object : MapLifeCycleCallback() {
                    override fun onMapDestroy() {
                        Timber.d("🗺️ [KakaoMap] 지도가 파괴되었습니다.")
                    }

                    override fun onMapError(error: Exception?) {
                        Timber.e(error, "🗺️ [KakaoMap] 지도 로딩 에러 발생!")
                    }
                },
                object : KakaoMapReadyCallback() {
                    override fun onMapReady(kakaoMap: KakaoMap) {
                        Timber.d("🗺️ [KakaoMap] 지도 렌더링 완료!")
                        kakaoMap.moveCamera(CameraUpdateFactory.zoomTo(16))

                        kakaoMap.setGestureEnable(GestureType.Zoom, false)          // 두 손가락 줌 끄기
                        kakaoMap.setGestureEnable(
                            GestureType.OneFingerZoom,
                            false
                        ) // 한 손가락 더블탭 줌 끄기
                        kakaoMap.setGestureEnable(
                            GestureType.Rotate,
                            false
                        )        // 지도가 돌아가는 것도 막으려면 추가
                        kakaoMap.setGestureEnable(
                            GestureType.Tilt,
                            false
                        )          // 지도가 눕혀지는 것도 막으려면 추가
                        kakaoMap.setOnLabelClickListener { _, _, label ->
                            currentOnLabelClick(label.labelId)
                            true
                        }
                        onMapReady(kakaoMap)
                    }
                }
            )
            mapView
        }
    )
}