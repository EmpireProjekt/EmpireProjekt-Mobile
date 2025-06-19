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
                // Local
                implementation(projects.modules.services.core.ui.common)
                implementation(projects.modules.services.core.ui.dialog)
                implementation(projects.modules.services.core.ui.sheet)
                implementation(projects.modules.services.core.ui.theme)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.theme.impl)
            }
        }

        val androidMain by getting {
            dependencies {
                // Accompanist
                implementation(libs.google.accompanist.systemuicontroller)
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.theme.ui"
}
