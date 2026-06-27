package com.hye.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.hye.common.design.theme.CoreColors
import com.hye.common.design.theme.CoreDimens
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
    // 1. 공통 치수는 DesignTheme에서 가져옴
    val dimens: CoreDimens
        @Composable
        @ReadOnlyComposable
        get() = DesignTheme.dimens

    // 2. 공통 시스템 컬러는 DesignTheme에서 가져옴
    val core: CoreColors
        @Composable
        @ReadOnlyComposable
        get() = DesignTheme.colors

    // 3. 프로젝트 특화 기능 컬러는 여기서 가져옴
    val feature: FeatureColors
        @Composable
        @ReadOnlyComposable
        get() = LocalFeatureColors.current
}