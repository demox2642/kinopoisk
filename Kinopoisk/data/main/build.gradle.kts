import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.demox.kinopoisk.data.main"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    api(project(Modules.Domain.MAIN))
    api(Dependencies.KotlinX.coroutines)
    api(Dependencies.Networking.okHttp)
    api(Dependencies.Networking.logging)
    api(Dependencies.Networking.retrofit)
    api(Dependencies.Networking.paging)
    api(Dependencies.Networking.retrofitGson)
    api(Dependencies.Di.koinCore)
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
}
