plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.HILT_GRADLE)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
}

android {
    namespace  = Apps.APPLICATION_ID
    compileSdk = Apps.COMPILE_SDK

    defaultConfig {
        applicationId = Apps.APPLICATION_ID
        minSdk        = Apps.MIN_SDK
        targetSdk     = Apps.TARGET_SDK
        versionCode   = Apps.VERSION_CODE
        versionName   = Apps.VERSION_NAME

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled   = Apps.IS_MINIFY_ENABLED
            isShrinkResources = Apps.IS_SHRINK_RESOURCES
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
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
    implementation(Libs.ANDROIDX_ROOM_KTX)
    implementation(Libs.ANDROIDX_VIEWMODEL)
    implementation(Libs.COMPOSE_LOTTIE)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.HILT_ANDROID)
    implementation(Libs.HILT_NAVIGATION)
    implementation(Libs.INFO_BAR)
    implementation(Libs.PICKER_SNAPPER)
    ksp(Libs.HILT_ANDROID_COMPILER)
}