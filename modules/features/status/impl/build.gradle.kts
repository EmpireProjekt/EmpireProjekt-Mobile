@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.util.requireProjectInfo

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.decompose.core)
                implementation(libs.klibs.mikro.core)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.ktor.client.core)
                implementation(libs.moko.resources.core)
                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.coroutines)
                implementation(libs.mvikotlin.main)
                implementation(projects.modules.features.status.api)
                implementation(projects.modules.services.core.common)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlin.coroutines.test)
                implementation(libs.tests.assertk)
                implementation(libs.tests.turbine)
            }
        }
    }
}
multiplatformResources {
    resourcesPackage.set("${requireProjectInfo.group}.status")
    resourcesClassName.set("SR")
}
