plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.jabama.core.common"
    compileSdk = 34

}

dependencies {

    implementation(libs.coroutines.android)
}