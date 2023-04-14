package library

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.androidTestImplementation
import util.debugImplementation
import util.implementation

class ComposePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            dependencies {
                implementation(Libs.ANDROIDX_CORE)
                implementation(Libs.COMPOSE_ACTIVITY)
                implementation(Libs.COMPOSE_LOTTIE)
                implementation(Libs.COMPOSE_MATERIAL)
                implementation(Libs.COMPOSE_UI)
                implementation(Libs.COMPOSE_VIEWMODEL)
                androidTestImplementation(Libs.COMPOSE_JUNIT)
                debugImplementation(Libs.COMPOSE_MANIFEST)
            }
        }
    }

}
