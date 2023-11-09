package library

import Libs
import util.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            dependencies {
                implementation(Libs.ANDROIDX_CORE)
                implementation(Libs.COMPOSE)
                implementation(Libs.COMPOSE_ACTIVITY)
                implementation(Libs.COMPOSE_LOTTIE)
                implementation(Libs.COMPOSE_MATERIAL)
                implementation(Libs.COMPOSE_VIEWMODEL)
            }
        }
    }

}