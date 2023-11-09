package util

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

internal fun DependencyHandler.annotationProcessor(dependency: Any) {
    add("annotationProcessor", dependency)
}

internal fun DependencyHandler.compileOnly(dependency: Any) {
    add("compileOnly", dependency)
}

internal fun DependencyHandler.runtimeOnly(dependency: Any) {
    add("runtimeOnly", dependency)
}

internal fun DependencyHandler.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

internal fun DependencyHandler.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}

internal fun DependencyHandler.kapt(dependency: Any) {
    add("kapt", dependency)
}

internal fun DependencyHandler.kaptAndroidTest(dependency: Any) {
    add("kaptAndroidTest", dependency)
}

internal fun DependencyHandler.ksp(dependency: Any) {
    add("ksp", dependency)
}

internal fun DependencyHandler.kspAndroidTest(dependency: Any) {
    add("kspAndroidTest", dependency)
}

internal fun DependencyHandler.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}