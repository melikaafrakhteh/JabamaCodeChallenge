plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.jabama.core.network"
    compileSdk = 34
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.okhttp.loggging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.coroutines.adapter)
    implementation(libs.koin)
}