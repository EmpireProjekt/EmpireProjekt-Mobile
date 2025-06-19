@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
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
                // Compose MPP
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.uiTooling)
                implementation(compose.preview)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                // Moko
                implementation(libs.moko.resources.core)
                // Local
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.ui.common)
                implementation(projects.modules.services.core.ui.dialog)
                implementation(projects.modules.services.core.ui.sheet)
                implementation(projects.modules.services.core.ui.option)
                implementation(projects.modules.services.core.ui.theme)
                implementation(projects.modules.features.webview.api)
                implementation(projects.modules.features.webview.impl)
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.webview.ui"
}
