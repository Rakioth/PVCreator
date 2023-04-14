// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.ANDROID_APPLICATION) version Versions.ANDROID_GRADLE apply false
    id(Plugins.ANDROID_LIBRARY)     version Versions.ANDROID_GRADLE apply false
    id(Plugins.KOTLIN_ANDROID)      version Versions.KOTLIN         apply false
    id(Plugins.HILT_GRADLE)         version Versions.HILT           apply false
}