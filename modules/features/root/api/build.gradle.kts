@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
}

kotlin {
    androidLibrary {
        withHostTest {}
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.decompose.core)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.services.core.common)
            }
        }
        val androidHostTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlin.serialization.json)
                implementation(projects.modules.features.rating.api)
            }
        }
    }
}
