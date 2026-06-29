plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.hye.features"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26
    }

}
kotlin {
    jvmToolchain(21)
}
