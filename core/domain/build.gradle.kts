plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.jabama.domain"
    compileSdk = 34
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
}