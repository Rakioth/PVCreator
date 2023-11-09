package library

import Libs
import Plugins
import util.implementation
import util.ksp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                apply(Plugins.KSP)
            }

            dependencies {
                implementation(Libs.ROOM)
                implementation(Libs.ROOM_RUNTIME)
                ksp(Libs.ROOM_COMPILER)
            }
        }
    }

}