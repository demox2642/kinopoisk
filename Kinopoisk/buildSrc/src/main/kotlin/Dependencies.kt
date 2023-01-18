

object Dependencies {
    object Plugins {
        const val Android = "com.android.tools.build:gradle:${Versions.gradle}"
        const val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    object KotlinX {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    }

    object Networking {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Di {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object Room {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomKTX = "androidx.room:room-ktx:${Versions.room}"
        const val roomPaging = "androidx.room:room-paging:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    }
}
