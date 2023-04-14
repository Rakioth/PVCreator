package module

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import util.configAndroid
import util.configBuildTypes
import util.implementation

class DomainPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(Plugins.ANDROID_LIBRARY)
                apply(Plugins.KOTLIN_ANDROID)
                apply(Plugins.JUNIT)
            }

            extensions.getByType<LibraryExtension>().apply {
                namespace = "${Apps.APPLICATION_ID}.domain"

                this.configAndroid()
                this.configBuildTypes()
            }

            dependencies {
                implementation(Libs.COROUTINES)
            }
        }
    }

}
