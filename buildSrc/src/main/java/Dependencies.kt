object Deps {

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${PluginVersion.KOTLIN_VERSION}"

    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX_VERSION}"

    // Fragments
    const val ANDROIDX_FRAGMENT_KTX =
        "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX_VERSION}"

    // AppCompat
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT_VERSION}"

    // Material
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL_VERSION}"

    // ConstraintLayout
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT_VERSION}"

    // RecyclerView
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Version.RECYCLER_VIEW_VERSION}"

    // ViewPager2
    const val VIEWPAGER2 = "androidx.viewpager2:viewpager2:${Version.VIEWPAGER2_VERSION}"

    // SwipeRefreshLayout
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Version.SWIPE_REFRESH_LAYOUT_VERSION}"


    // Lifecycle, ViewModel and LiveData

    // ViewModel
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE_VERSION}"

    // LiveData
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE_VERSION}"

    // Saved state module for ViewModel
    const val LIFECYCLE_VIEWMODEL_SAVEDSTATE =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.LIFECYCLE_VERSION}"

    // alternately - if using Java8, use the following instead of lifecycle-compiler
    const val LIFECYCLE_COMMON_JAVA8 =
        "androidx.lifecycle:lifecycle-common-java8:${Version.LIFECYCLE_VERSION}"

    // Navigation Components
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION_VERSION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION_VERSION}"
    const val NAVIGATION_RUNTIME =
        "androidx.navigation:navigation-runtime-ktx:${Version.NAVIGATION_VERSION}"

    // Dynamic Feature Module Support
    const val NAVIGATION_DYNAMIC =
        "androidx.navigation:navigation-dynamic-features-fragment:${Version.NAVIGATION_VERSION}"

    // Coroutines
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES_VERSION}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES_VERSION}"

    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT_VERSION}"
    const val RETROFIT_MOSHI_CONVERTER =
        "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT_VERSION}"


    // Moshi
    const val MOSHI = "com.squareup.moshi:moshi:${Version.MOSHI}"
    const val MOSHI_COMPILER = "com.squareup.moshi:moshi-kotlin-codegen:${Version.MOSHI}"

    // okhttp client
    const val OK_HTTP3 = "com.squareup.okhttp3:okhttp:${Version.OK_HTTP3_VERSION}"
    const val OK_HTTP3_LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${Version.OK_HTTP3_VERSION}"

    // glide
    const val GLIDE = "com.github.bumptech.glide:glide:${Version.GLIDE_VERSION}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Version.GLIDE_VERSION}"

    //koin
    const val KOIN = "org.koin:koin-android:${Version.KOIN}"
    const val KOIN_CORE = "org.koin:koin-core:${Version.KOIN}"
    const val KOIN_VIEW_MODEL = "org.koin:koin-androidx-viewmodel:${Version.KOIN}"
    const val KOIN_ANDROIDX_SCOPE = "org.koin:koin-androidx-scope:${Version.KOIN}"
}

object TestDeps {

    // (Required) Writing and executing Unit Tests on the JUnit Platform
    const val JUNIT5_API = "org.junit.jupiter:junit-jupiter-api:${TestVersion.junit5Version}"
    const val JUNIT5_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${TestVersion.junit5Version}"

    // (Optional) If you need "Parameterized Tests"
    const val JUNIT5_PARAMS = "org.junit.jupiter:junit-jupiter-params:${TestVersion.junit5Version}"

    const val ANDROIDX_CORE_TESTING =
        "androidx.arch.core:core-testing:${TestVersion.archTestingVersion}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${TestVersion.robolectricVersion}"

    // AndroidX Test - JVM testing
    const val ANDROIDX_TEST_CORE_KTX =
        "androidx.test:core-ktx:${TestVersion.androidXTestCoreVersion}"
    const val ANDROIDX_JUNIT =
        "androidx.test.ext:junit:${TestVersion.androidXTestExtKotlinRunnerVersion}"

    const val ANDROIDX_ESPRESSO =
        "androidx.test.espresso:espresso-contrib:${TestVersion.espressoVersion}"

    // Coroutines test
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestVersion.coroutinesTestVersion}"

    // MockWebServer
    const val MOCK_WEB_SERVER =
        "com.squareup.okhttp3:mockwebserver:${TestVersion.mockWebServerVersion}"

    // Gson
    const val GSON = "com.google.code.gson:gson:${Version.GSON_VERSION}"

    // MockK
    const val MOCK_K = "io.mockk:mockk:${TestVersion.mockKVersion}"

    // Truth
    const val TRUTH = "com.google.truth:truth:${TestVersion.truthVersion}"

    // Espresso
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${TestVersion.espressoVersion}"

    // Testing Navigation
    const val NAVIGATION_TEST =
        "androidx.navigation:navigation-testing:${Version.NAVIGATION_VERSION}"
    const val KOIN_TEST="org.koin:koin-test:${Version.KOIN}"

}

