buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Plugins.CLASSPATH_GRADLE)
        classpath(Plugins.CLASSPATH_NAV_SAFE_ARGS)
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