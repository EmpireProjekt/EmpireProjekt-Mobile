@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    alias(libs.plugins.kotlin.compose.gradle)
}
kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose)
                // Compose extensions
                implementation(libs.composeext.shimmer)
                // Moko
                implementation(libs.moko.resources.core)
                implementation(libs.moko.resources.compose)
                // Compose
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.preview)
                implementation(compose.uiTooling)
                implementation(compose.runtime)
                implementation(libs.placeholder)
                // Local
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.services.core.ui.theme)
            }
        }
        val androidMain by getting {
            dependencies {
                // Image loading
                implementation("io.coil-kt:coil-compose:2.7.0")
            }
        }
    }
}
android {
    namespace = "${requireProjectInfo.group}.core.ui"
}
