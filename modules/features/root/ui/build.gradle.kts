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
                // Compose MPP
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.uiTooling)
                implementation(compose.preview)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose)
                // Local
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.buildKonfig)
                implementation(projects.modules.services.core.ui.common)
                implementation(projects.modules.services.core.ui.dialog)
                implementation(projects.modules.services.core.ui.sheet)
                implementation(projects.modules.services.core.ui.theme)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.root.impl)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.features.rating.impl)
                implementation(projects.modules.features.rating.ui)
                implementation(projects.modules.features.splash.impl)
                implementation(projects.modules.features.splash.ui)
                implementation(projects.modules.features.status.api)
                implementation(projects.modules.features.status.impl)
                implementation(projects.modules.features.status.ui)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.theme.impl)
                implementation(projects.modules.features.theme.ui)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.features.towns.impl)
                implementation(projects.modules.features.towns.ui)
                implementation(projects.modules.features.info.ui)
                implementation(projects.modules.features.map.ui)
                implementation(projects.modules.features.votes.ui)
                implementation(projects.modules.features.tabs.impl)
                implementation(projects.modules.features.tabs.ui)
                implementation(projects.modules.features.webview.impl)
                implementation(projects.modules.features.webview.ui)
            }
        }
        val androidMain by getting {
            dependencies {
                // Accompanist
//                implementation(libs.google.accompanist.systemuicontroller)
                // Image loading
//                implementation("io.coil-kt:coil:2.4.0")
//                implementation("io.coil-kt:coil-compose:2.4.0")
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.root.ui"
}
