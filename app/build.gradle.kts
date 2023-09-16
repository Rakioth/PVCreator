plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT_GRADLE)
}

android {
    namespace  = "com.raks.pvcreator"
    compileSdk = Apps.COMPILE_SDK

    defaultConfig {
        applicationId             = Apps.APPLICATION_ID
        minSdk                    = Apps.MIN_SDK
        targetSdk                 = Apps.TARGET_SDK
        versionCode               = Apps.VERSION_CODE
        versionName               = Apps.VERSION_NAME
        testInstrumentationRunner = Apps.TEST_INSTRUMENTATION_RUNNER

        vectorDrawables {
            useSupportLibrary = true
        }
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.ANDROIDX_COMPOSE
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(Libs.ANDROIDX_ACTIVITY)
    implementation(Libs.ANDROIDX_COMPOSE)
    implementation(Libs.ANDROIDX_CORE_KTX)
    implementation(Libs.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
    implementation(Libs.ANDROIDX_ROOM_KTX)
    implementation(Libs.ANDROIDX_VIEWMODEL)
    implementation(Libs.COMPOSE_LOTTIE)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_PREVIEW)
    implementation(Libs.HILT_ANDROID)
    implementation(Libs.NAVIGATION_COMPOSE)
    implementation(Libs.NAVIGATION_HILT)
    implementation(Libs.PICKER_SNAPPER)
    kapt(Libs.HILT_ANDROID_COMPILER)
    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.ANDROIDX_TEST_ESPRESSO)
    androidTestImplementation(Libs.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Libs.COMPOSE_JUNIT)
    debugImplementation(Libs.COMPOSE_MANIFEST)
    debugImplementation(Libs.COMPOSE_TOOLING)
}