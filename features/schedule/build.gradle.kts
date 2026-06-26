plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hye.features.schedule"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}
kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(":common")

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.compose)

    implementation(libs.timber)
}