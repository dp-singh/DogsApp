import extension.addAppModuleDependencies
import extension.addInstrumentationTestDependencies
import extension.addUnitTestDependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}
android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = "com.example.testapp"
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        val sharedTestDir = "src/sharedTest/java"
        getByName("test") {
            java.srcDir(sharedTestDir)
        }
        getByName("androidTest") {
            java.srcDir(sharedTestDir)
        }
    }
    androidExtensions {
        isExperimental = true
    }
    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))
    addAppModuleDependencies()
    addUnitTestDependencies()
    addInstrumentationTestDependencies()
}