package com.hye.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.hye.common.design.theme.DesignTheme

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    // Feature 컬러 주입
    CompositionLocalProvider(
        LocalFeatureColors provides LightFeatureColors
    ) {
        // 내부는 DesignTheme으로 감싸서 Core 컬러와 Dimens, MaterialTheme을 적용
        DesignTheme {
            content()
        }
    }
}

// 앱 개발 시 실제로 사용할 최상위 접근 Object
object AppTheme {
    val color: FeatureColors
        @Composable
        @ReadOnlyComposable
        get() = LocalFeatureColors.current

    val dimens: AppDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalFeatureDimens.current
}