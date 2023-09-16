plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

android {
    namespace  = "com.raks.pvcreator.data"
    compileSdk = Apps.COMPILE_SDK

    defaultConfig {
        minSdk                    = Apps.MIN_SDK
        testInstrumentationRunner = Apps.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = Apps.JAVA_COMPATIBILITY_VERSION
        targetCompatibility = Apps.JAVA_COMPATIBILITY_VERSION
    }

    kotlinOptions {
        jvmTarget = Apps.JVM_TARGET
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Libs.ANDROIDX_ROOM_KTX)
    implementation(Libs.ANDROIDX_ROOM_RUNTIME)
    implementation(Libs.DATASTORE_PREFERENCES)
    kapt(Libs.ANDROIDX_ROOM_COMPILER)
}