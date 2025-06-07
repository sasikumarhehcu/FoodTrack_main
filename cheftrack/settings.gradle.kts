pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("org.jetbrains.kotlin.android") version "1.9.23" // ✅ upgraded
        id("org.jetbrains.kotlin.kapt") version "1.9.23"
        id("com.android.application") version "8.2.2"
        id("com.google.gms.google-services") version "4.4.0"
    }
}


dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ChefTrack"
include(":app")
