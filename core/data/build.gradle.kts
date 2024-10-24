plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.jabama.core.data"
    compileSdk = 34

}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.coroutines.core)
}