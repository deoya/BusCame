plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.secrets.gradle)
}

android {
    namespace = "com.hye.buscame"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.hye.buscame"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}
secrets {
    propertiesFileName = "local.properties"
}
kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":common:design"))
    implementation(project(":features:schedule"))
    implementation(project(":features:route"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.compose)

    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.timber)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    implementation(libs.kakao.map)

    implementation(libs.bundles.network)
    compileOnly(libs.error.prone.annotations)
}