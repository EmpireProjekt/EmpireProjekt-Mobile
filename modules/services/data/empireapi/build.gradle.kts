@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor
                implementation(libs.ktor.client.core)
                // Serialization
                implementation(libs.kotlin.serialization.json)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // Local
                implementation(projects.modules.services.core.buildKonfig)
                implementation(projects.modules.services.core.common)
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.api.empireapi"
}
