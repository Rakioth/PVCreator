import org.gradle.api.JavaVersion

object Apps {
    const val APPLICATION_ID              = "com.raks.pvcreator"
    const val COMPILE_SDK                 = 33
    const val MIN_SDK                     = 26
    const val TARGET_SDK                  = 33
    const val VERSION_CODE                = 1
    const val VERSION_NAME                = "1.0.0"
    const val JVM_TARGET                  = "1.8"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
          val JAVA_COMPATIBILITY_VERSION  = JavaVersion.VERSION_1_8
}