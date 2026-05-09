@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.util.requireProjectInfo

plugins {
    id("com.android.kotlin.multiplatform.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    alias(libs.plugins.kotlin.serialization)
    id("dev.icerock.mobile.multiplatform-resources")
    id("ru.astrainteractive.gradleplugin.android.namespace")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // kotlin
                implementation(libs.kotlin.serialization.json)
                // klibs
                implementation(libs.klibs.mikro.core)
                // Decompose
                implementation(libs.decompose.core)
                // MviKotlin
                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.main)
                implementation(libs.mvikotlin.coroutines)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // Moko
                implementation(libs.moko.resources.core)
                // Ktor
                implementation(libs.ktor.client.core)
                // Local
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.features.status.api)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.tests.assertk)
                implementation(libs.tests.turbine)
                implementation(libs.kotlin.coroutines.test)
            }
        }
    }
}
multiplatformResources {
    resourcesPackage.set("${requireProjectInfo.group}.status")
    resourcesClassName.set("SR")
}
