package extension

import Deps
import TestDeps
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds required dependencies to app module
 */
fun DependencyHandler.addAppModuleDependencies() {

    implementation(Deps.KOTLIN)
    implementation(Deps.ANDROIDX_CORE_KTX)
    implementation(Deps.ANDROIDX_FRAGMENT_KTX)

    // Support and Widgets
    implementation(Deps.APPCOMPAT)
    implementation(Deps.MATERIAL)
    implementation(Deps.CONSTRAINT_LAYOUT)
    implementation(Deps.RECYCLER_VIEW)
    implementation(Deps.VIEWPAGER2)
    implementation(Deps.SWIPE_REFRESH_LAYOUT)

    // Lifecycle, LiveData, ViewModel
    implementation(Deps.LIFECYCLE_LIVEDATA_KTX)
    implementation(Deps.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Deps.LIFECYCLE_VIEWMODEL_SAVEDSTATE)
    implementation(Deps.LIFECYCLE_COMMON_JAVA8)

    // Navigation Components
    implementation(Deps.NAVIGATION_FRAGMENT)
    implementation(Deps.NAVIGATION_UI)
    implementation(Deps.NAVIGATION_RUNTIME)

    // Coroutines
    implementation(Deps.COROUTINES_CORE)
    implementation(Deps.COROUTINES_ANDROID)

    // Glide
    implementation(Deps.GLIDE)
    kapt(Deps.GLIDE_COMPILER)

    //Koin
    implementation(Deps.KOIN)
    implementation(Deps.KOIN_CORE)
    implementation(Deps.KOIN_VIEW_MODEL)
    implementation(Deps.KOIN_ANDROIDX_SCOPE)

    // Retrofit
    implementation(Deps.RETROFIT)
    implementation(Deps.RETROFIT_MOSHI_CONVERTER)

    // Moshi
    implementation(Deps.MOSHI)
    kapt(Deps.MOSHI_COMPILER)
}

fun DependencyHandler.addNetworkModuleDependencies() {
    implementation(Deps.KOTLIN)
    // Coroutines
    implementation(Deps.COROUTINES_CORE)
    implementation(Deps.COROUTINES_ANDROID)

    //Koin
    implementation(Deps.KOIN)
    implementation(Deps.KOIN_CORE)

    // Retrofit
    implementation(Deps.RETROFIT)
    implementation(Deps.RETROFIT_MOSHI_CONVERTER)
    implementation(Deps.OK_HTTP3)
    implementation(Deps.OK_HTTP3_LOGGING)

    // Moshi
    implementation(Deps.MOSHI)
    kapt(Deps.MOSHI_COMPILER)
}

fun DependencyHandler.addLocalDbDependencies() {
    implementation(Deps.ROOM_RUNTIME)
    kapt(Deps.ROOM_COMPILER)
    implementation(Deps.ROOM_KTX)
}

fun DependencyHandler.addRepositoryModuleDependencies() {
    implementation(Deps.KOTLIN)
    // Coroutines
    implementation(Deps.COROUTINES_CORE)
    implementation(Deps.COROUTINES_ANDROID)

    //Koin
    implementation(Deps.KOIN)
    implementation(Deps.KOIN_CORE)
}

/**
 * Adds Unit test dependencies
 */
fun DependencyHandler.addUnitTestDependencies() {

    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation(TestDeps.JUNIT5_API)
    testRuntimeOnly(TestDeps.JUNIT5_ENGINE)

    // (Optional) If you need "Parameterized Tests"
    testImplementation(TestDeps.JUNIT5_PARAMS)
    testImplementation(TestDeps.ANDROIDX_CORE_TESTING)
    testImplementation(TestDeps.ROBOLECTRIC)

    // AndroidX Test - JVM testing
    testImplementation(TestDeps.ANDROIDX_TEST_CORE_KTX)

    testImplementation(TestDeps.ANDROIDX_JUNIT)

    // Coroutines Test
    testImplementation(TestDeps.COROUTINES_TEST)

    // MockWebServer
    testImplementation(TestDeps.MOCK_WEB_SERVER)

    // MockK
    testImplementation(TestDeps.MOCK_K)
    // Truth
    testImplementation(TestDeps.TRUTH)
    //KOIN
    testImplementation(TestDeps.KOIN_TEST)
    //KFixture
    testImplementation(TestDeps.K_FIXTURE)
}

fun DependencyHandler.addInstrumentationTestDependencies() {

    // AndroidX Test - Instrumented testing
    androidTestImplementation(TestDeps.ANDROIDX_JUNIT)
    androidTestImplementation(TestDeps.ANDROIDX_CORE_TESTING)

    // Espresso
    androidTestImplementation(TestDeps.ANDROIDX_ESPRESSO)

    // Testing Navigation
    androidTestImplementation(TestDeps.NAVIGATION_TEST)

    // Coroutines Test
    androidTestImplementation(TestDeps.COROUTINES_TEST)

    // MockWebServer
    androidTestImplementation(TestDeps.MOCK_WEB_SERVER)
    // Gson
    androidTestImplementation(TestDeps.GSON)

    // MockK
    androidTestImplementation(TestDeps.MOCK_K)
    // Truth
    androidTestImplementation(TestDeps.TRUTH)
    androidTestImplementation(TestDeps.K_FIXTURE)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL
 * syntax in module\build.gradle.kts files.
 */
@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

private fun DependencyHandler.testRuntimeOnly(dependencyNotation: Any): Dependency? =
    add("testRuntimeOnly", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }

    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T