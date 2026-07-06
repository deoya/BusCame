plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.hye.features"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

}
kotlin {
    jvmToolchain(21)
}
