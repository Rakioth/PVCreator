import org.gradle.api.JavaVersion

object Apps {
    const val APPLICATION_ID             = "com.raks.pvcreator"
    const val COMPILE_SDK                = 33
    const val MIN_SDK                    = 26
    const val TARGET_SDK                 = 33
    const val VERSION_CODE               = 1
    const val VERSION_NAME               = "1.0.0"
    const val IS_MINIFY_ENABLED          = true
    const val IS_SHRINK_RESOURCES        = true
    const val JVM_TARGET                 = "17"
          val JAVA_COMPATIBILITY_VERSION = JavaVersion.VERSION_17
}