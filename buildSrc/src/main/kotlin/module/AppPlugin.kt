package module

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project
import util.configAndroid
import util.configApplication
import util.implementation

class AppPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                apply(Plugins.ANDROID_APPLICATION)
                apply(Plugins.KOTLIN_ANDROID)
                apply(Plugins.KOTLIN_COMPOSE)
                apply(Plugins.COMPOSE)
                apply(Plugins.HILT)
                apply(Plugins.ANDROID_JUNIT)
            }

            extensions.getByType<BaseAppModuleExtension>().apply {
                this.configApplication()
                this.configAndroid()
            }

            dependencies {
                implementation(project(":data"))
                implementation(project(":domain"))
                implementation(Libs.INFO_BAR)
                implementation(Libs.PICKER_SNAPPER)
                implementation(Libs.SPLASH_SCREEN)
            }
        }
    }

}
