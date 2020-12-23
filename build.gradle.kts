buildscript {
    val kotlin_version by extra("1.4.21")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Plugins.CLASSPATH_GRADLE)
        classpath(kotlin("gradle-plugin", version = PluginVersion.KOTLIN_VERSION))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}