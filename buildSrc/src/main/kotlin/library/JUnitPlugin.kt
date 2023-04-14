package library

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.testImplementation

class JUnitPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            dependencies {
                testImplementation(Libs.ANDROIDX_ESPRESSO)
                testImplementation(Libs.ANDROIDX_JUNIT)
                testImplementation(Libs.JUNIT)
                testImplementation(Libs.TRUTH)
            }
        }
    }

}
