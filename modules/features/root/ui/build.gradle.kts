@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.materialIconsExtended)
                implementation(libs.decompose.compose)
                implementation(libs.decompose.core)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.material)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.preview)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.tooling)
                implementation(libs.jetbrains.compose.ui)
                implementation(projects.modules.features.info.ui)
                implementation(projects.modules.features.map.ui)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.features.rating.impl)
                implementation(projects.modules.features.rating.ui)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.root.impl)
                implementation(projects.modules.features.splash.impl)
                implementation(projects.modules.features.splash.ui)
                implementation(projects.modules.features.status.api)
                implementation(projects.modules.features.status.impl)
                implementation(projects.modules.features.status.ui)
                implementation(projects.modules.features.tabs.impl)
                implementation(projects.modules.features.tabs.ui)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.theme.impl)
                implementation(projects.modules.features.theme.ui)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.features.towns.impl)
                implementation(projects.modules.features.towns.ui)
                implementation(projects.modules.features.votes.ui)
                implementation(projects.modules.features.webview.impl)
                implementation(projects.modules.features.webview.ui)
                implementation(projects.modules.services.core.buildKonfig)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.ui.common)
                implementation(projects.modules.services.core.ui.dialog)
                implementation(projects.modules.services.core.ui.sheet)
                implementation(projects.modules.services.core.ui.theme)
            }
        }
    }
}
