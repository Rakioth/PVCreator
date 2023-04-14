package library

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.androidTestImplementation

class AndroidJUnitPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            dependencies {
                androidTestImplementation(Libs.ANDROIDX_ESPRESSO)
                androidTestImplementation(Libs.ANDROIDX_JUNIT)
                androidTestImplementation(Libs.JUNIT)
                androidTestImplementation(Libs.TRUTH)
            }
        }
    }

}
