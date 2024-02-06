import org.gradle.api.JavaVersion

object Apps {
    const val APPLICATION_ID              = "com.raks.pvcreator"
    const val COMPILE_SDK                 = 34
    const val MIN_SDK                     = 26
    const val TARGET_SDK                  = 34
    const val VERSION_CODE                = 2
    const val VERSION_NAME                = "1.0.1"
    const val TEST_INSTRUMENTATION_RUNNER = "${APPLICATION_ID}.HiltTestRunner"
    const val IS_MINIFY_ENABLED           = true
    const val IS_SHRINK_RESOURCES         = true
          val JAVA_COMPATIBILITY_VERSION  = JavaVersion.VERSION_17
}