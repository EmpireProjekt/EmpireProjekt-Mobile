@file:Suppress("UnusedPrivateMember")

plugins {
    id("org.jetbrains.compose")
    id("com.android.kotlin.multiplatform.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    alias(libs.plugins.kotlin.compose.gradle)
    id("ru.astrainteractive.gradleplugin.android.namespace")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose MPP
                implementation(compose.materialIconsExtended)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.material)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.preview)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.tooling)
                implementation(libs.jetbrains.compose.ui)
                // Local
                implementation(projects.modules.services.core.ui.common)
                implementation(projects.modules.services.core.ui.dialog)
                implementation(projects.modules.services.core.ui.sheet)
                implementation(projects.modules.services.core.ui.theme)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.theme.impl)
            }
        }
    }
}
