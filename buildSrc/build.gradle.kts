import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object BuildConfig {
    const val ANDROID_GRADLE = "8.9.1"
    const val KOTLIN_GRADLE  = "2.1.20"
          val JVM_TARGET     = JvmTarget.JVM_17
}

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:${BuildConfig.ANDROID_GRADLE}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildConfig.KOTLIN_GRADLE}")
    implementation("com.squareup:javapoet:1.13.0")
}

kotlin {
    compilerOptions {
        jvmTarget = BuildConfig.JVM_TARGET
    }
}

gradlePlugin {
    plugins {
        register("library.android-junit") {
            id                  = "library.android-junit"
            implementationClass = "library.AndroidJUnitPlugin"
        }
        register("library.junit") {
            id                  = "library.junit"
            implementationClass = "library.JUnitPlugin"
        }
        register("library.compose") {
            id                  = "library.compose"
            implementationClass = "library.ComposePlugin"
        }
        register("library.hilt") {
            id                  = "library.hilt"
            implementationClass = "library.HiltPlugin"
        }
        register("library.room") {
            id                  = "library.room"
            implementationClass = "library.RoomPlugin"
        }
        register("module.app") {
            id                  = "module.app"
            implementationClass = "module.AppPlugin"
        }
        register("module.data") {
            id                  = "module.data"
            implementationClass = "module.DataPlugin"
        }
        register("module.domain") {
            id                  = "module.domain"
            implementationClass = "module.DomainPlugin"
        }
    }
}
