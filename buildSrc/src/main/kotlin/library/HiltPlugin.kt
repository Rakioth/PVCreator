package library

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.androidTestImplementation
import util.implementation
import util.ksp
import util.kspAndroidTest

class HiltPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                apply(Plugins.HILT_GRADLE)
                apply(Plugins.KSP)
            }

            dependencies {
                implementation(Libs.HILT)
                implementation(Libs.HILT_NAVIGATION)
                ksp(Libs.HILT_COMPILER)
                androidTestImplementation(Libs.HILT_TESTING)
                kspAndroidTest(Libs.HILT_COMPILER)
            }
        }
    }

}
