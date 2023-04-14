package module

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project
import util.androidTestImplementation
import util.configAndroid
import util.configBuildTypes
import util.implementation

class DataPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(Plugins.ANDROID_LIBRARY)
                apply(Plugins.KOTLIN_ANDROID)
                apply(Plugins.ROOM)
                apply(Plugins.HILT)
                apply(Plugins.ANDROID_JUNIT)
            }

            extensions.getByType<LibraryExtension>().apply {
                namespace = "${Apps.APPLICATION_ID}.data"

                defaultConfig {
                    testInstrumentationRunner = Apps.HILT_INSTRUMENTATION_RUNNER
                }

                this.configAndroid()
                this.configBuildTypes()
            }

            dependencies {
                implementation(project(":domain"))
                implementation(Libs.DATASTORE_PREFERENCES)
                androidTestImplementation(Libs.COROUTINES_TEST)
            }
        }
    }

}
